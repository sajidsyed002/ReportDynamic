package com.report.config.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
//import com.lowagie.text.*;
import com.report.config.service.TaskDefinitionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("reports/pdf")
public class ReportGeneration {

    @Autowired
    private TaskDefinitionBean taskDefinitionBean;

    private final ObjectMapper objectMapper = new ObjectMapper();

    ////to generate report at once without schedule
    @GetMapping("/generate/{id}")
    public ResponseEntity<String> generatePdf(@PathVariable("id") Long reportId) {
        try {
            String outputPath = taskDefinitionBean.generatePDFReport(reportId);
            return ResponseEntity.ok("PDF created successfully at: " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to create PDF: " + e.getMessage());
        }
    }
}

