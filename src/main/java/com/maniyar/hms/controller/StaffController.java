package com.maniyar.hms.controller;

import com.maniyar.hms.dto.StaffDTO;
import com.maniyar.hms.service.StaffService;
import com.maniyar.hms.util.QRCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class StaffController {

    private final StaffService staffService;

    @PostMapping
    public ResponseEntity<StaffDTO> createStaff(@RequestBody StaffDTO dto) {
        return ResponseEntity.ok(staffService.createStaff(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffDTO> updateStaff(@PathVariable Long id,
                                                @RequestBody StaffDTO dto) {
        return ResponseEntity.ok(staffService.updateStaff(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return ResponseEntity.ok("Staff deleted");
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDTO> getStaff(@PathVariable Long id) {
        return ResponseEntity.ok(staffService.getStaffById(id));
    }

    @GetMapping
    public ResponseEntity<List<StaffDTO>> getAllStaff() {
        return ResponseEntity.ok(staffService.getAllStaff());
    }

    @GetMapping("/qr/{id}")
    public ResponseEntity<byte[]> downloadQR(@PathVariable Long id) throws Exception {

        StaffDTO staff = staffService.getStaffById(id);

        byte[] qrImage = QRCodeGenerator.generateQRCode(staff.getQrCode());

        return ResponseEntity.ok()
                .header("Content-Type", "image/png")
                .body(qrImage);
    }
}