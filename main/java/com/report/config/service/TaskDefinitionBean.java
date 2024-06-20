package com.report.config.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.report.config.dto.ReportConfigurationDTO;
import com.report.config.entity.ScheduleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

@Service
public class TaskDefinitionBean implements Runnable {

   // @Autowired
    private ScheduleConfig scheduleConfig;

    static int i =1;

    @Override
    public void run() {
        System.out.println("Running action for report : " + scheduleConfig.getReportId());
        System.out.println("With cron Data: " + scheduleConfig.getCronExpression());
        try {
            String outputPath = generatePDFReport(scheduleConfig.getReportId());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public ScheduleConfig getScheduleConfig() {
        return scheduleConfig;
    }

    public void setScheduleConfig(ScheduleConfig scheduleConfig) {
        this.scheduleConfig = scheduleConfig;
    }

    public String generatePDFReport(Long reportId) throws FileNotFoundException {
        String outputPath = "C:\\Users\\Wissen\\Desktop\\";
        try {
            String uri = "http://localhost:8084/reports/" + reportId;
            ReportConfigurationDTO reportConfigurationDTO = new RestTemplate().getForObject(uri, ReportConfigurationDTO.class);

            File file = new File(outputPath+"report"+i++ +".pdf");
            FileOutputStream fos = new FileOutputStream(file);
            PdfWriter writer = new PdfWriter(fos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
            // Create a table with two columns
            Table table = new Table(2);
            // Add header row
            table.addHeaderCell(new Cell().add(new Paragraph("Report Name")));
            table.addHeaderCell(new Cell().add(new Paragraph(reportConfigurationDTO.getReportName())));
            reportConfigurationDTO.getColumnConfigs().stream().forEach(mapsData->{
                mapsData.entrySet().forEach(entry->{
                    table.addCell(new Cell().add(new Paragraph(entry.getKey())));
                    table.addCell(new Cell().add(new Paragraph(entry.getValue().toString())));
                });
            });

            document.add(table);
            document.close();

           /* for(Map<String, Object> iter : reportConfigurationDTO.getColumnConfigs()) {
                for (Map.Entry<String, Object> entry : iter.entrySet()) {
                    table.addCell(new Cell().add(new Paragraph(entry.getKey())));
                    table.addCell(new Cell().add(new Paragraph(entry.getValue().toString())));
                }
            }*/
            // Add key-value pairs as rows
        /*    Iterator<Map.Entry<String, Object>> fields =  reportConfigurationDTO.getColumnConfigs().iterator();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                table.addCell(new Cell().add(entry.getKey()));
                table.addCell(new Cell().add(entry.getValue().toString()));
            }*/
        }
        catch (Exception e){
            e.printStackTrace();
        }
        if(i==10) i=1;
        return outputPath;
    }


}
