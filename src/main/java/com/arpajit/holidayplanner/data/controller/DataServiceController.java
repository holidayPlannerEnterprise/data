package com.arpajit.holidayplanner.data.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.arpajit.holidayplanner.dto.ConsumeMessage;
import com.arpajit.holidayplanner.data.service.*;

@RestController
@RequestMapping("/dataservice")
public class DataServiceController {
    private static final Logger logger = LoggerFactory.getLogger(DataServiceController.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DatabaseComm databaseComm;

    @PostMapping("/add-audit")
    public String getAddAudit(HttpServletRequest httpRequest, @RequestBody ConsumeMessage message) throws Exception {
        logger.info("Requested {}: {}", httpRequest.getMethod(), httpRequest.getRequestURL());
        logger.info("Requested Payload: {}", objectMapper.writeValueAsString(message));
        return databaseComm.addAudit(message);
    }

    @GetMapping("/update-audit")
    public String getUpdateAudit(HttpServletRequest httpRequest,
                                    @RequestParam String traceId,
                                    @RequestParam String status,
                                    @RequestParam String statusResp) {
        logger.info("Requested {}: {}", httpRequest.getMethod(), httpRequest.getRequestURL());
        logger.info("Requested Update for:\n\ttraceId: {}\n\tstatus: {}\n\tstatusResp: {}",
                        traceId, status, statusResp);
        return databaseComm.updateAudit(traceId, status, statusResp);
    }
}
