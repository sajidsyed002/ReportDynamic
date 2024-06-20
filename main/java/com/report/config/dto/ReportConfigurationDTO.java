package com.report.config.dto;

import java.util.List;
import java.util.Map;

public class ReportConfigurationDTO {

    private Long reportId;
    private String reportName;
    private String reportType;
    private List<Map< String, Object >> columnConfigs;

    public List<Map<String, Object>> getColumnConfigs() {
        return columnConfigs;
    }

    public void setColumnConfigs(List<Map<String, Object>> columnConfigs) {
        this.columnConfigs = columnConfigs;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public ReportConfigurationDTO(Long reportId, String reportName, String reportType) {
        this.reportId = reportId;
        this.reportName = reportName;
        this.reportType = reportType;
    }
    public ReportConfigurationDTO(){

    }
}
