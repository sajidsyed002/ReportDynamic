package com.report.config.controller;

import com.report.config.dto.ReportDataDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class ReportConfigControllerTest {

    private RestTemplate restTemplate;

    @Test
    void fetchReportList() {
    }

    @Test
    void fetchColumnList() {
    }

    @Test
    void fetchReportWithColumn() {
        int id =2;
        String uri = "http://localhost:8084/reports/"+id;

        ResponseEntity<ReportDataDTO> respEntity = new RestTemplate().getForEntity(uri,
                ReportDataDTO.class);

        assertEquals(id,respEntity.getBody().getReportId());
        assertEquals(HttpStatus.OK,respEntity.getStatusCode());
    }

    @Test
    void fetchReportWithData() {
    }
}
