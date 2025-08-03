package com.arpajit.holidayplanner.data.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.arpajit.holidayplanner.data.service.*;
import com.arpajit.holidayplanner.data.dto.KafkaMessage;
import com.arpajit.holidayplanner.data.dto.HolidayAllFields;

@RestController
@RequestMapping("/dataservice")
public class DataServiceController {
    private static final Logger logger = LoggerFactory.getLogger(DataServiceController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DatabaseComm databaseComm;

    @PostMapping("/add-audit")
    public String getAddAudit(HttpServletRequest httpRequest, @RequestBody KafkaMessage message) throws Exception {
        logger.info("Requested {}: {}", httpRequest.getMethod(), httpRequest.getRequestURL());
        logger.info("Requested Payload: {}", objectMapper.writeValueAsString(message));
        return databaseComm.addAudit(message);
    }

    @PostMapping("/update-audit")
    public String getUpdateAudit(HttpServletRequest httpRequest, @RequestBody Map<String, String> request) {
        logger.info("Requested {}: {}", httpRequest.getMethod(), httpRequest.getRequestURL());
        String traceId = request.get("traceId");
        String status = request.get("status");
        String statusResp = request.get("statusResp");
        logger.info("Requested Update for:\n\ttraceId: {}\n\tstatus: {}\n\tstatusResp: {}",
                        traceId, status, statusResp);
        return databaseComm.updateAudit(traceId, status, statusResp);
    }

    @GetMapping("/all-holiday-details")
    public List<HolidayAllFields> getAllHolidayDetails(HttpServletRequest httpRequest) throws Exception {
        logger.info("Requested {}: {}", httpRequest.getMethod(), httpRequest.getRequestURL());
        return databaseComm.allHolidayDetails();
    }
}
