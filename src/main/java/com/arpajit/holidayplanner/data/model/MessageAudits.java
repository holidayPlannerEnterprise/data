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
    private String traceId;
    private String msgRequestType;
    private String msgSourceService;
    private LocalDateTime msgTimestamp;
    private String msgStatus;
    private String msgStatusResp;
}
