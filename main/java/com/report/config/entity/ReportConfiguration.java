package com.report.config.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class ReportConfiguration{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;
    private String reportName;
    private String reportType;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "BRIDGE_CONFIGURATION",
            joinColumns = @JoinColumn(name = "report_id"),
            inverseJoinColumns = @JoinColumn(name = "column_id"))
    private List<ColumnConfiguration> columns;

    public List<ColumnConfiguration> getColumns() {
        return columns;
    }
    public void setColumns(List<ColumnConfiguration> columns) {
        this.columns = columns;
    }


    @Override
    public int hashCode() {
        return Objects.hash(reportType, reportId, reportName);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ReportConfiguration other = (ReportConfiguration) obj;
        return Objects.equals(reportType, other.reportType)
                && Objects.equals(reportId, other.reportId) && Objects.equals(reportName, other.reportName);
    }


    public ReportConfiguration(Long reportId, String reportName, String reportType) {
        this.reportId = reportId;
        this.reportName = reportName;
        this.reportType = reportType;
    }

    public ReportConfiguration() {

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

    @Override
    public String toString() {
        return "ReportConfiguration{" +
                "reportId=" + reportId +
                ", reportName='" + reportName + '\'' +
                ", reportType='" + reportType + '\'' +
                ", columns=" + columns +
                '}';
    }
}
