package com.report.config.entity_pgsql;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Column_config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnConfigPg {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "column_id")
    private Long columnId;
    @Column(name = "column_name")
    private String columnName;

}