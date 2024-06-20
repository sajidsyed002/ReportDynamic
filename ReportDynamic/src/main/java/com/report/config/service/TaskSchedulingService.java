package com.report.config.service;

import com.report.config.entity.ScheduleConfig;
import com.report.config.repository.IScheduleConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

@Service
public class TaskSchedulingService {

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private IScheduleConfigRepository iScheduleConfigRepository;

    @Autowired
    private TaskDefinitionBean taskDefinitionBean;

    Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public void scheduleATask(Long reportId){
        Optional<ScheduleConfig> scheduleConfigOpt = iScheduleConfigRepository.findById(reportId);
        if(scheduleConfigOpt.isPresent()){
            taskDefinitionBean.setScheduleConfig(scheduleConfigOpt.get());
            String cronExpression = taskDefinitionBean.getScheduleConfig().getCronExpression();
            System.out.println("Scheduling task for reportId : " + reportId + " and cron expression: " + cronExpression);
            ScheduledFuture<?> scheduledTask = taskScheduler.schedule(taskDefinitionBean, new CronTrigger(cronExpression, TimeZone.getTimeZone(TimeZone.getDefault().getID())));
            //jobsMap.put(reportId.toString(), scheduledTask);
        }else{
           throw new NullPointerException("report id not found in ScheduleConfig table");
        }
    }

    public void removeScheduledTask(String jobId) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(jobId);
        if(scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.put(jobId, null);
        }
    }

}
