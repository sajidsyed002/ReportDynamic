package com.report.config.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "column_config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "column_id")
    private Long columnId;
    @Column(name = "column_name")
    private String columnName;

/*    @OneToMany(mappedBy = "columnConfig", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ColumnMetadataConfig> columnMetadataConfigs;

    /* private String color;
    private Long dataLength;
    private String fontStyle;
    private Long fontSize;*/
   /*@OneToMany(mappedBy = "columnId", cascade = CascadeType.ALL)
   private List<ColumnMetadataConfig> columnMetadataConfig;*/

}