package com.arpajit.holidayplanner.data.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageAudits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer msgId;
    private String msgRequestType;
    private String msgSourceService;
    private LocalDateTime msgTimestamp;
    private String msgPayload;
}
