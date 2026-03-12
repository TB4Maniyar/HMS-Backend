package com.maniyar.hms.service;

import com.maniyar.hms.dto.AttendanceDTO;
import com.maniyar.hms.entity.Attendance;
import com.maniyar.hms.entity.Staff;
import com.maniyar.hms.repository.AttendanceRepository;
import com.maniyar.hms.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StaffRepository staffRepository;

    public AttendanceDTO scanQR(String qrCode) {

        Staff staff = staffRepository.findByQrCode(qrCode)
                .orElseThrow(() -> new RuntimeException("Invalid QR Code"));

        Optional<Attendance> openAttendance =
                attendanceRepository
                        .findTopByStaffIdAndExitTimeIsNullOrderByEntryTimeDesc(staff.getId());

        if (openAttendance.isPresent()) {

            // EXIT SCAN
            Attendance attendance = openAttendance.get();

            attendance.setExitTime(LocalDateTime.now());

            double hours = Duration
                    .between(attendance.getEntryTime(), attendance.getExitTime())
                    .toMinutes() / 60.0;

            attendance.setTotalHours(hours);

            Attendance saved = attendanceRepository.save(attendance);

            return mapToDTO(saved);

        } else {

            // ENTRY SCAN
            Attendance attendance = Attendance.builder()
                    .staff(staff)
                    .entryTime(LocalDateTime.now())
                    .build();

            Attendance saved = attendanceRepository.save(attendance);

            return mapToDTO(saved);
        }
    }

    private AttendanceDTO mapToDTO(Attendance attendance) {

        return AttendanceDTO.builder()
                .staffId(attendance.getStaff().getId())
                .staffName(attendance.getStaff().getName())
                .entryTime(attendance.getEntryTime())
                .exitTime(attendance.getExitTime())
                .totalHours(attendance.getTotalHours())
                .build();
    }

    public List<AttendanceDTO> getTodayAttendance() {

        LocalDate today = LocalDate.now();

        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.atTime(23,59,59);

        return attendanceRepository
                .findByEntryTimeBetween(start, end)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<AttendanceDTO> getCurrentlyInside() {

        return attendanceRepository
                .findByExitTimeIsNull()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<AttendanceDTO> getAllLogs() {

        return attendanceRepository
                .findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<AttendanceDTO> getMonthlyAttendance(int year, int month) {

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(23,59,59);

        return attendanceRepository
                .findByEntryTimeBetween(start, end)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<Attendance> getAll() {
            return attendanceRepository.findAll();
    }
}