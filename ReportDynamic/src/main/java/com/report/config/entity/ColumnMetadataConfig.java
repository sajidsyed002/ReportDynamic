package com.report.config.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "COLUMN_METADATA_CONFIG")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ColumnMetadataId.class)
public class ColumnMetadataConfig {

    @Id
    @Column(name = "METADATA_ID")
    private Long metadataId;
    @Id
    @Column(name="COLUMN_ID")
    private Long columnId;

    @Column(name="METADATA_VALUE")
    private String metadataValue;



 /*   @ManyToOne
     @MapsId("columnId")
     private ColumnConfig columnConfig;

     @ManyToOne
     @MapsId("metadataId")
     private MetadataConfig metadataConfig;*/



 /*   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "column_id")
    private ColumnConfig columnConfig;

    //@Id
   // @Column(name="COLUMN_CONFIG_ID")
    //private Long columnId;
    */

    //@AttributeOverride(name="name", @Column(name="columnId"))
    // @Id
    //@EmbeddedId
    //private ColumnMetadataId id;

}
