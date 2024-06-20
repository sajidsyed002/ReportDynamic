package com.report.config.repository;

import com.report.config.entity.MetadataConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMetadataConfigRepository extends JpaRepository<MetadataConfig, Long> {

}
