package com.report.config.service;

import com.report.config.entity.ScheduleConfig;
import com.report.config.repository.ScheduleConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
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
    private ScheduleConfigRepository scheduleConfigRepository;

    @Autowired
    private TaskDefinitionBean taskDefinitionBean;

    Map<String, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public void scheduleATask(Long reportId){
        Optional<ScheduleConfig> scheduleConfigOpt = scheduleConfigRepository.findById(reportId);
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

   /* public static void generateCSVReport(JSONObject reportData, JSONObject configData, String filePath) throws IOException {
        JSONArray reportDataArray = reportData.getJSONArray("reportData");
        JSONArray columnConfigs = configData.getJSONArray("reportColumnConfigs");
        int datalll = 0; // Initialize datalll
        if (reportDataArray.length() == 0) {
            System.out.println("No data available.");
            return;
        }

        // Extract headers based on column configurations
        List<String> headers = new ArrayList<>();

        for (int i = 0; i < columnConfigs.length(); i++) {
            JSONObject columnConfig = columnConfigs.getJSONObject(i).getJSONObject("columnConfig");
            if (columnConfig.getBoolean("include")) {
                headers.add(columnConfig.getString("columnName"));

            }
            String columnName = columnConfig.getString("columnName");
            datalll = columnConfig.optInt("dataLength", columnName.length()); // Use specified data length or length of column name

            // Trim the column name if its length exceeds the specified data length
            if (columnName.length() > datalll) {
                columnName = columnName.substring(0, datalll);
            }
        }

        // Write CSV file
        try (FileWriter csvWriter = new FileWriter(filePath)) {
            // Write headers


            for (int i = 0; i < headers.size(); i++) {
                csvWriter.append(headers.get(i).substring(datalll));

                if (i < headers.size() - 1) {
                    csvWriter.append(",");
                }
            }
            csvWriter.append("\n");

            // Write data
            for (int i = 0; i < reportDataArray.length(); i++) {
                JSONObject entry = reportDataArray.getJSONObject(i);
                for (int j = 0; j < headers.size(); j++) {
                    String value = entry.optString(headers.get(j), "");
                    csvWriter.append(value);
                    if (j < headers.size() - 1) {
                        csvWriter.append(",");
                    }
                }
                csvWriter.append("\n");
            }
        }

        System.out.println("CSV report generated successfully!");
    }*/
}
