package com.arpajit.holidayplanner.data;

import java.time.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableHolidays {
    @Id
    private String holId;
    private LocalDate holDt;
    private String holName;
    private String holType;
    private String holSource;
    private LocalDateTime createdDt;
    private LocalDateTime modifiedDt;
    private String createdBy;
    private String modifiedBy;
    private String modRemarks;
}
