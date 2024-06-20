package com.report.config.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.report.config.dto.ReportDataDTO;
import com.report.config.entity.ScheduleConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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
            String uri = "http://localhost:8084/report/" + reportId;
            ReportDataDTO reportDataDTO = new RestTemplate().getForObject(uri, ReportDataDTO.class);

            File file = new File(outputPath+"report"+i++ +".pdf");
            FileOutputStream fos = new FileOutputStream(file);
            PdfWriter writer = new PdfWriter(fos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
            // Create a table with two columns
            Table table = new Table(2);
            // Add header row
            table.addHeaderCell(new Cell().add(new Paragraph("Report Name")));
            table.addHeaderCell(new Cell().add(new Paragraph(reportDataDTO.getReportName())));
            reportDataDTO.getData().stream().forEach(mapsData->{
                mapsData.entrySet().forEach(entry->{
                    table.addCell(new Cell().add(new Paragraph(entry.getKey())));
                    table.addCell(new Cell().add(new Paragraph(entry.getValue().toString())));
                });
            });

            document.add(table);
            document.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        if(i==7) i=1;
        return outputPath;
    }

   /* public void generatePdfReport1(Long reportId) throws IOException, DocumentException {
       // List<HedfeFund> funds = getFunds();
        String filePath = "C:\\Users\\Wissen\\Desktop\\";
        //List<ColumnConfig> columnConfigs = getColumnConfig(reportId);
        String uri = "http://localhost:8084/reports/" + reportId;
        ReportConfigDTO reportConfigurationDTO = new RestTemplate().getForObject(uri, ReportConfigDTO.class);

        File file = new File(filePath+"report"+i++ +".pdf");
        FileOutputStream fos = new FileOutputStream(file);
        PdfWriter writer = new PdfWriter(fos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);
        // Create a table with two columns
        Table table = new Table(2);

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        PdfPTable table = new PdfPTable(columnConfigs.size());

        // Add header row
        for (ColumnConfig config : columnConfigs) {
            PdfPCell cell = new PdfPCell(new Phrase(config.getColumnName()));
            Font font = FontFactory.getFont(config.getFontStyle(), config.getFontSize(), new BaseColor(Color.decode(config.getColor())));
            cell.setPhrase(new Phrase(config.getColumnName(), font));
            table.addCell(cell);
        }

        // Add data rows
        for (HedfeFund fund : funds) {
            for (ColumnConfig config : columnConfigs) {
                String cellValue = "";
               /* switch (config.getColumnName()) {
                    case "column1":
                        cellValue = fund.getColumn1();
                        break;
                    case "column2":
                        cellValue = fund.getColumn2();
                        break;
                    // Add cases for other columns
                }
                PdfPCell cell = new PdfPCell(new Phrase(cellValue));
                table.addCell(cell);
            }
        }

        document.add(table);
        document.close();
    }*/


}
