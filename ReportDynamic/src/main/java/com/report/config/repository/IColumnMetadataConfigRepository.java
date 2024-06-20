package com.report.config.repository;

import com.report.config.entity.ColumnMetadataConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IColumnMetadataConfigRepository extends JpaRepository<ColumnMetadataConfig, Long> {
   // List<ColumnMetadataConfig> findByIdColumnId(Long columnId);
}
