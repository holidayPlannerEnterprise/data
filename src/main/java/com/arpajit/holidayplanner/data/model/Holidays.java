package com.arpajit.holidayplanner.data.model;

import java.time.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Holidays {
    @Id
    @Column(insertable = false, updatable = false)
    private String holId;

    private LocalDate holDt;
    private String holName;
    private String holType;
    private String holSource;
    
    @Column(insertable = false, updatable = false)
    private LocalDateTime createdDt;

    @Column(insertable = false, updatable = false)
    private LocalDateTime modifiedDt;

    @Column(insertable = false, updatable = false)
    private String createdBy;

    @Column(insertable = false, updatable = false)
    private String modifiedBy;

    private String modRemarks;
}
