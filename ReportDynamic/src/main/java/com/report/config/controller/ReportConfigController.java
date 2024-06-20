package com.report.config.controller;


import java.util.List;
import java.util.Optional;

import com.report.config.dto.ReportConfigDTO;
import com.report.config.dto.ReportDataDTO;
import com.report.config.entity.ColumnConfig;
import com.report.config.entity.ReportConfig;
import com.report.config.entity_pgsql.ReportConfigPg;
import com.report.config.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportConfigController {

    @Autowired
    private ReportConfigService reportConfigurationService;

    @Autowired
    private MetadataConfigService metadataConfigService;

    @Autowired
    private DataService dataService;

    @Autowired
    private TaskSchedulingService taskSchedulingService;

    @Autowired
    private TaskDefinitionBean taskDefinitionBean;

    @GetMapping("reports")
    public List<ReportConfig> fetchReportList()
    {
        return reportConfigurationService.fetchReportList();
    }

    //to get reports from both H2 and pgsql databases
    @GetMapping("reports/all")
    public ResponseEntity<List<ReportConfigPg>> fetchAllReportsList()
    {
        return reportConfigurationService.fetchAllReportsList();
    }

    @GetMapping("/reports/columns")
    public List<ColumnConfig> fetchColumnList()
    {
        return reportConfigurationService.fetchColumnList();
    }

    @GetMapping("/reports/columns/{id}")
    public ResponseEntity<ReportConfig> fetchReportWithColumns(@PathVariable("id") Long reportId)
    {
        return reportConfigurationService.fetchReportWithColumn(reportId);
    }

    @PostMapping("/reports/save")
    public ReportConfig saveReport(@RequestBody ReportConfig reportConfiguration)
    {
        return reportConfigurationService.saveReport(reportConfiguration);
    }
//===============================================================================================\

    //to fetch report config with Data
    @GetMapping("/report/{id}")
    public ResponseEntity<ReportDataDTO> fetchReportWithData(@PathVariable("id") Long reportId) throws Exception
    {
        return dataService.fetchReportWithData(reportId);
    }

    //to fetch report with metadata configs
    @GetMapping("/report/column/{id}")
    public ResponseEntity<ReportConfigDTO> fetchReportWithColumnMetadata(@PathVariable("id") Long reportId) throws Exception
    {
        return metadataConfigService.fetchReportWithColumnMetadata(reportId);
    }

    //to save/insert data into all tables from reportData.txt file
    @GetMapping("/metadata/save")
    public List<ReportConfig> saveReportWithMetadata() throws Exception
    {
        return metadataConfigService.saveReportWithMetadata();
    }

    //to generate report for a given schedule cron expression
    @GetMapping("/report/generate/{reportId}")
    public ResponseEntity<String> getReport(@PathVariable Long reportId) {

        try {
            taskSchedulingService.scheduleATask(reportId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Report "+reportId+" is being generated");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to generate Report "+reportId+" "+e);
        }
    }




  /*  @GetMapping("/metadata/{columnId}")
 /*   public List<ColumnMetadataConfig> getColumnMetadata(@PathVariable Long columnId) {
        return metadataConfigService.getColumnMetadata(columnId);
    }*/

 /*   @GetMapping("/{columnId}")
    public Optional<ColumnConfig> getColumnById(@PathVariable Long columnId) {
        return metadataConfigService.getColumnById(columnId);
    }*/






        /* String uri = "http://localhost:8084/reports/"+reportId;
            ReportConfigDTO reportConfigurationDTO = new RestTemplate().getForObject(uri,
                                                                        ReportConfigDTO.class);
            List<Map<String, Object>> data = reportConfigurationDTO.getColumnConfigs();
            HttpHeaders headers = new HttpHeaders();
             // headers.add("Content-Disposition", "inline; filename=report.pdf");*/
}


