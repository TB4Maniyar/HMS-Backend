package com.maniyar.hms.service;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public byte[] generateBill(String patientName, String treatment, double amount) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Hospital Bill"));
            document.add(new Paragraph("---------------------------"));
            document.add(new Paragraph("Patient Name: " + patientName));
            document.add(new Paragraph("Treatment: " + treatment));
            document.add(new Paragraph("Amount: ₹" + amount));

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return out.toByteArray();
    }
}