package com.report.config.repository;

import com.report.config.entity.ReportConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReportConfigRepository extends JpaRepository<ReportConfig, Long> {

}
