package com.maniyar.hms.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceDTO {

    private Long staffId;
    private String staffName;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Double totalHours;
}
