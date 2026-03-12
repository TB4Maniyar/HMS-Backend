package com.maniyar.hms.controller;

import com.maniyar.hms.dto.AttendanceDTO;
import com.maniyar.hms.entity.Attendance;
import com.maniyar.hms.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/scan")
    public ResponseEntity<AttendanceDTO> scanQR(@RequestBody Map<String, String> body) {
        String qrCode = body.get("qrCode");
        AttendanceDTO result = attendanceService.scanQR(qrCode);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/today")
    public ResponseEntity<List<AttendanceDTO>> todayAttendance() {
        return ResponseEntity.ok(attendanceService.getTodayAttendance());
    }

    @GetMapping("/present")
    public ResponseEntity<List<AttendanceDTO>> currentlyInside() {
        return ResponseEntity.ok(attendanceService.getCurrentlyInside());
    }

    @GetMapping("/logs")
    public ResponseEntity<List<AttendanceDTO>> logs() {
        return ResponseEntity.ok(attendanceService.getAllLogs());
    }

    @GetMapping("/monthly")
    public ResponseEntity<List<AttendanceDTO>> monthlyAttendance(
            @RequestParam int year,
            @RequestParam int month) {

        return ResponseEntity.ok(
                attendanceService.getMonthlyAttendance(year, month)
        );
    }

    @GetMapping
    public ResponseEntity<List<AttendanceDTO>> getAllAttendance() {
        return ResponseEntity.ok(attendanceService.getAllLogs());
    }
}
