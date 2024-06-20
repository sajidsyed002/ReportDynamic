package com.report.config.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "METADATA_CONFIG")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetadataConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "METADATA_ID")
    private Long metadataId;
    @Column(name = "METADATA_NAME")
    private String metadataName;

 /*   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "COLUMN_METADATA_CONFIG",
            joinColumns = @JoinColumn(name = "metadata_id"),
            inverseJoinColumns = @JoinColumn(name = "column_id"))
    private List<ColumnConfig> columns;*/
}
