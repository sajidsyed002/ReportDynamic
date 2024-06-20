package com.report.config.controller;


import java.util.List;
import java.util.Map;

import com.report.config.dto.ReportConfigurationDTO;
import com.report.config.service.TaskDefinitionBean;
import com.report.config.service.TaskSchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.report.config.entity.ReportConfiguration;
import com.report.config.entity.ColumnConfiguration;
import com.report.config.service.ReportConfigurationService;
import org.springframework.web.client.RestTemplate;

@RestController
    public class ReportConfigurationController {

        @Autowired
        private ReportConfigurationService reportConfigurationService;

        @Autowired
        private TaskSchedulingService taskSchedulingService;

    @Autowired
    private TaskDefinitionBean taskDefinitionBean;

        @GetMapping("reports")
        public List<ReportConfiguration> fetchReportList()
        {
            return reportConfigurationService.fetchReportList();
        }

        @GetMapping("/reports/columns")
        public List<ColumnConfiguration> fetchColumnList()
        {
            return reportConfigurationService.fetchColumnList();
        }

        @GetMapping("/reports/columns/{id}")
        public ResponseEntity<ReportConfiguration> fetchReportWithColumn(@PathVariable("id") Long reportId)
        {
            return reportConfigurationService.fetchReportWithColumn(reportId);
        }

        //to fetch report config with Data
        @GetMapping("/reports/{id}")
        public ResponseEntity<ReportConfigurationDTO> fetchReportWithData(@PathVariable("id") Long reportId)
        {
            return reportConfigurationService.fetchReportWithData(reportId);
        }

        //to load metadata from .txt file into DB
        @GetMapping("/metadata/load")
        public List<ReportConfiguration> loadReports()
        {
            return reportConfigurationService.loadReports();
        }

        @PostMapping("/reports/save")
        public ReportConfiguration saveReport(@RequestBody ReportConfiguration reportConfiguration)
        {
            return reportConfigurationService.saveReport(reportConfiguration);
        }

        //to generate report for a given schedule cron expression
        @GetMapping("/reports/generate/{reportId}")
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




        /* String uri = "http://localhost:8084/reports/"+reportId;
            ReportConfigurationDTO reportConfigurationDTO = new RestTemplate().getForObject(uri,
                                                                        ReportConfigurationDTO.class);
            List<Map<String, Object>> data = reportConfigurationDTO.getColumnConfigs();
            HttpHeaders headers = new HttpHeaders();
             // headers.add("Content-Disposition", "inline; filename=report.pdf");*/
    }

