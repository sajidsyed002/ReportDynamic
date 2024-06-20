package com.report.config.repository;

import com.report.config.entity.ColumnConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IColumnConfigRepository extends CrudRepository<ColumnConfig, Long> {

}