package com.maniyar.hms.controller;

import com.maniyar.hms.dto.DashboardDTO;
import com.maniyar.hms.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardDTO> getDashboard(){
        return ResponseEntity.ok(
                dashboardService.getDashboardData()
        );
    }
}