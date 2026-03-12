package com.maniyar.hms.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffDTO {
    private Long id;
    private String name;
    private String mobile;
    private String department;
    private String qrCode;
}