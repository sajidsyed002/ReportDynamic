package com.report.config.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.report.config.entity.ReportConfiguration;

@Repository
public interface ReportConfigurationRepository extends JpaRepository<ReportConfiguration, Long> {

}
