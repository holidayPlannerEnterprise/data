package com.arpajit.holidayplanner.data.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpajit.holidayplanner.data.model.MessageAudits;
import com.arpajit.holidayplanner.data.repository.HolidaysRepository;
import com.arpajit.holidayplanner.data.repository.MessageAuditsRepository;
import com.arpajit.holidayplanner.dto.KafkaMessage;
import com.arpajit.holidayplanner.dto.HolidayAllFields;

@Service
public class DatabaseComm {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseComm.class);

    @Autowired
    private MessageAuditsRepository messageAuditsRepository;

    @Autowired
    private HolidaysRepository holidaysRepository;

    public String addAudit(KafkaMessage message) {
        MessageAudits messageAudit = new MessageAudits();
        messageAudit.setTraceId(message.getTraceId());
        messageAudit.setMsgRequestType(message.getRequestType());
        messageAudit.setMsgSourceService(message.getSourceService());
        messageAudit.setMsgTimestamp(LocalDateTime.parse(message.getTimestamp()));
        messageAudit.setMsgStatus(message.getStatus());
        messageAudit.setMsgStatusResp(message.getStatusResp());
        messageAuditsRepository.save(messageAudit);
        logger.trace("Added audit to MessageAudits table");
        return "SUCCESS";
    }

    public String updateAudit(String traceId, String status, String statusResp) {
        MessageAudits messageAudit = messageAuditsRepository.findByTraceId(traceId);
        messageAudit.setMsgStatus(status);
        messageAudit.setMsgStatusResp(statusResp);
        messageAuditsRepository.save(messageAudit);
        logger.info("Updated audit to MessageAudits table");
        return "SUCCESS";
    }

    public List<HolidayAllFields> allHolidayDetails() throws Exception {
        return holidaysRepository.findAll()
                                    .stream()
                                    .map(h -> new HolidayAllFields(
                                        h.getHolId(),
                                        h.getHolDt(),
                                        h.getHolName(),
                                        h.getHolType(),
                                        h.getHolSource(),
                                        h.getCreatedDt(),
                                        h.getModifiedDt(),
                                        h.getCreatedBy(),
                                        h.getModifiedBy(),
                                        h.getModRemarks()))
                                    .toList();
    }
}
