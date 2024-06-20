package com.report.config.repository;

import com.report.config.entity.ColumnConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnConfigurationRepository extends CrudRepository<ColumnConfiguration, Long>{

}
