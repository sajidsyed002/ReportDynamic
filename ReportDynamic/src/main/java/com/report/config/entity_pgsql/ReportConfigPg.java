package com.report.config.entity_pgsql;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Report_Config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportConfigPg{

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId;
    @Column(name = "report_name")
    private String reportName;
    @Column(name = "report_type")
    private String reportType;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "REPORT_COLUMN_CONFIG",
            joinColumns = @JoinColumn(name = "report_id"),
            inverseJoinColumns = @JoinColumn(name="column_id"))
    private List<ColumnConfigPg> columns;

}
