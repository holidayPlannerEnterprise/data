package com.arpajit.holidayplanner.data.repository;

import java.util.*;
import java.time.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arpajit.holidayplanner.data.model.MessageAudits;

@Repository
public interface MessageAuditsRepository extends JpaRepository<MessageAudits, String> {
    List<MessageAudits> findAll();
    List<MessageAudits> findByTraceId(int traceId);
    List<MessageAudits> findByMsgRequestType(String msgRequestType);
    List<MessageAudits> findByMsgSourceService(String msgSourceService);
    List<MessageAudits> findByMsgTimestampBetween(LocalDateTime start, LocalDateTime end);
    boolean existsByMsgId(int msgId);
}
