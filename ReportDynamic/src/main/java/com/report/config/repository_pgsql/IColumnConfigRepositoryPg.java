package com.report.config.repository_pgsql;

import com.report.config.entity_pgsql.ColumnConfigPg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IColumnConfigRepositoryPg extends JpaRepository<ColumnConfigPg, Long> {

}