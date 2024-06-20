package com.report.config.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnMetadataId implements Serializable {

    @Column(name = "METADATA_ID")
    private Long metadataId;
    @Column(name="COLUMN_ID")
    private Long columnId;
}
