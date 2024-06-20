package com.report.config.repository;

import com.report.config.entity.ScheduleConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IScheduleConfigRepository extends JpaRepository<ScheduleConfig, Long> {

}
