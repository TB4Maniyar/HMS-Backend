package com.maniyar.hms.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardDTO {

    private long totalStaff;
    private long todayAttendance;
    private long currentlyInside;
}