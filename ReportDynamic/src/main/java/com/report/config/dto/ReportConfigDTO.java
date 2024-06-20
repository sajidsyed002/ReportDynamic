package com.report.config.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportConfigDTO {

    private Long reportId;
    private String reportName;
    private String reportType;
    private List<ColumnMetadataDTO> columnConfigs;
}
