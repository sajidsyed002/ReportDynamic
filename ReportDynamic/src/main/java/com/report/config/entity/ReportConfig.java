package com.report.config.entity;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Entity(name = "report_config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportConfig{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId;
    @Column(name = "report_name")
    private String reportName;
    @Column(name = "report_type")
    private String reportType;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "REPORT_COLUMN_CONFIG",
            joinColumns = @JoinColumn(name = "report_id"),
            inverseJoinColumns = @JoinColumn(name = "column_id"))
    private List<ColumnConfig> columns;


}
