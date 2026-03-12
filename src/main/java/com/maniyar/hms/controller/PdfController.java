package com.maniyar.hms.controller;

import com.maniyar.hms.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pdf")
@CrossOrigin(origins = "http://localhost:5173")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @GetMapping("/bill")
    public ResponseEntity<byte[]> generateBill() {

        byte[] pdf = pdfService.generateBill(
                "Rahul Sharma",
                "General Checkup",
                500
        );

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=bill.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}