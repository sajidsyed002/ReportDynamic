package com.report.config.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportDataDTO {

    private Long reportId;
    private String reportName;
    private String reportType;
    private List<Map< String, Object >> data;
}

