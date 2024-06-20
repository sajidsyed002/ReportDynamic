package com.report.config.repository_pgsql;

import com.report.config.entity_pgsql.ReportConfigPg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReportConfigRepositoryPg extends JpaRepository<ReportConfigPg, Long> {

}
