package com.report.config.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnMetadataDTO {

    private String columnName;

    private HashMap<String, String> metadataConfigs;
}
