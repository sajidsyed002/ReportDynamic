package com.report.config.service;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

import com.report.config.dto.ReportConfigurationDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.query.ResultListTransformer;
import org.hibernate.query.sql.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.report.config.entity.ReportConfiguration;
import com.report.config.entity.ColumnConfiguration;
import com.report.config.entity.HedgeData;
import com.report.config.repository.ColumnConfigurationRepository;
import com.report.config.repository.ReportConfigurationRepository;

@Service
public class ReportConfigurationService {

    @Autowired
    private ReportConfigurationRepository reportConfigurationRepository;

    @Autowired
    private ColumnConfigurationRepository columnConfigurationRepository;

    @Autowired //@PersistentContext
    private EntityManager entityManager;


    // to fetch all the reports along with columns
    public List<ReportConfiguration> fetchReportList() {
        return (List<ReportConfiguration>) reportConfigurationRepository.findAll();
    }

    // to fetch all the columns
    public List<ColumnConfiguration> fetchColumnList() {
        return (List<ColumnConfiguration>) columnConfigurationRepository.findAll();
    }

    // to fetch individual report
    public ResponseEntity<ReportConfiguration> fetchReportWithColumn(Long reportId) {
        Optional<ReportConfiguration> reportConfiguration = reportConfigurationRepository.findById(reportId);
        if (reportConfiguration.isPresent()) {
            return new ResponseEntity<>(reportConfiguration.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ReportConfiguration(), HttpStatus.NOT_FOUND);

    }

    //to fetch Hedge data for a report
    public ResponseEntity<ReportConfigurationDTO> fetchReportWithData(Long reportId) {
        Optional<ReportConfiguration> reportConfiguration = reportConfigurationRepository.findById(reportId);
        if (reportConfiguration.isPresent()) {
           return fetchData(reportConfiguration.get());
        }
        return new ResponseEntity<>(new ReportConfigurationDTO(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ReportConfigurationDTO> fetchData(ReportConfiguration reportConfiguration) {

        StringBuilder builder = new StringBuilder();
        ReportConfigurationDTO reportConfigurationDTO = new ReportConfigurationDTO();
        builder.append("SELECT ");
        List<ColumnConfiguration> list = reportConfiguration.getColumns();
        String[] columnNames = new String[list.size()];
        for(int i=0;i<list.size();i++){
            if(i!=list.size()-1) {
                builder.append(list.get(i).getColumnName() + ", ");
                columnNames[i] = list.get(i).getColumnName();
            }
            else{
                builder.append(list.get(i).getColumnName()+" FROM HEDGE_DATA");
                columnNames[i] = list.get(i).getColumnName();
            }
        }
        // Create query
        String nativeQuery = builder.toString();
        Query query = entityManager.createNativeQuery(nativeQuery);
        List<Object[]> results = query.getResultList();

        List<Map< String, Object >> listMap = new ArrayList<>();
        for(Object[] row : results){
            Map<String, Object> rowMap = new HashMap<>();
            for(int i=0; i< columnNames.length;i++)
                rowMap.put(columnNames[i], row[i]);
            listMap.add(rowMap);
        }

        System.out.println("===="+listMap);

        reportConfigurationDTO.setReportId(reportConfiguration.getReportId());
        reportConfigurationDTO.setReportName(reportConfiguration.getReportName());
        reportConfigurationDTO.setReportType(reportConfiguration.getReportType());
        reportConfigurationDTO.setColumnConfigs(listMap);
        //  Query query = entityManager.createNativeQuery(nativeQuery, ReportDataDTO.class);
        return new ResponseEntity<>(reportConfigurationDTO, HttpStatus.OK);
    }

    public ReportConfiguration saveReport(ReportConfiguration report) {
        return reportConfigurationRepository.save(report);
    }


    //method to load reports from .txt file
    public List<ReportConfiguration> loadReports() {

        List<ReportConfiguration> reportConfigurationList = new ArrayList<>();

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("reportData.txt").getFile());
            Scanner input = new Scanner(file);
             while (input.hasNextLine()) {
                ReportConfiguration reportConfiguration = new ReportConfiguration();
                 List<ColumnConfiguration> columns = new ArrayList<>();
                String[] sections = input.nextLine().split("\\|");
                 //System.out.println("11======="+Arrays.toString(sections));
                 reportConfiguration.setReportName(sections[0].split(",")[0]);
                 reportConfiguration.setReportType(sections[0].split(",")[1]);

                 String[] columnConfigs = sections[1].split(";");
                for (int i = 0; i < columnConfigs.length; i++) {
                    String[] configs = columnConfigs[i].split(",");
                        ColumnConfiguration columnConfiguration = new ColumnConfiguration();
                        columnConfiguration.setColumnName(configs[0]);
                        columnConfiguration.setColor(configs[1]);
                        columnConfiguration.setDataLength(Long.valueOf(configs[2]));
                        columnConfiguration.setFontStyle(configs[3]);
                        columnConfiguration.setFontSize(Long.valueOf(configs[4]));
                        columns.add(columnConfiguration);
                }
                reportConfiguration.setColumns(columns);
                //System.out.println(reportConfiguration);
                reportConfigurationList.add(reportConfiguration);
            }
             reportConfigurationRepository.saveAll(reportConfigurationList);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return reportConfigurationList;
    }
 /*   NativeQueryImpl<List<Map< String, Object >>> nativeQueryImpl = (NativeQueryImpl) query;
        //nativeQueryImpl.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        //nativeQueryImpl.setTupleTransformer();
        List<Map< String, Object >> listMap = new ArrayList<>();//query.getResultList();
        //System.out.println("===="+listMap);
        nativeQueryImpl.setResultTransformer(new ResultListTransformer<List<Map< String, Object >>>() {
            @Override
            public List<List<Map< String, Object >>> transformList(List<List<Map< String, Object >>> list) {
                return list;
            }
        });
        listMap = (List<Map< String, Object >>) query.getResultList();
        */
}
