package com.report.config.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleConfig {

    @Id
    private Long reportId;
    private String cronExpression;

    // Getters and setters

}
