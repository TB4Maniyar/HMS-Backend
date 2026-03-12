package com.maniyar.hms.service;

import com.maniyar.hms.dto.DashboardDTO;
import com.maniyar.hms.repository.AttendanceRepository;
import com.maniyar.hms.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final StaffRepository staffRepository;
    private final AttendanceRepository attendanceRepository;

    public DashboardDTO getDashboardData(){

        long totalStaff = staffRepository.count();

        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.atTime(23,59,59);

        long todayAttendance =
                attendanceRepository.countByEntryTimeBetween(start,end);

        long currentlyInside =
                attendanceRepository.findByExitTimeIsNull().size();

        return DashboardDTO.builder()
                .totalStaff(totalStaff)
                .todayAttendance(todayAttendance)
                .currentlyInside(currentlyInside)
                .build();
    }
}