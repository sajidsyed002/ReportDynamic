package com.report.config.service;


import java.io.File;
import java.util.*;

import com.report.config.entity.ColumnConfig;
import com.report.config.entity.ReportConfig;
import com.report.config.entity_pgsql.ReportConfigPg;
import com.report.config.repository.IReportConfigRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.report.config.repository.IColumnConfigRepository;

@Service
public class ReportConfigService {

    @Autowired
    private IReportConfigRepository iReportConfigRepository;

    @Autowired
    private IColumnConfigRepository iColumnConfigRepository;

    @Autowired
    private ReportConfigPgsqlService reportConfigPgsqlService;

    @Autowired //@PersistentContext
    private EntityManager entityManager;


    // to fetch all the reports along with columns
    public List<ReportConfig> fetchReportList() {
        return (List<ReportConfig>) iReportConfigRepository.findAll();
    }

    // to fetch all the columns
    public List<ColumnConfig> fetchColumnList() {
        return (List<ColumnConfig>) iColumnConfigRepository.findAll();
    }

    // to fetch individual report
    public ResponseEntity<ReportConfig> fetchReportWithColumn(Long reportId) {
        Optional<ReportConfig> reportConfigOpt = iReportConfigRepository.findById(reportId);
        if (reportConfigOpt.isPresent()) {
            return new ResponseEntity<>(reportConfigOpt.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ReportConfig(), HttpStatus.NOT_FOUND);

    }



    public ReportConfig saveReport(ReportConfig report) {
        return iReportConfigRepository.save(report);
    }

    public ResponseEntity<List<ReportConfigPg>> fetchAllReportsList() {
       return new ResponseEntity<>(reportConfigPgsqlService.fetchReportList(), HttpStatus.OK);
    }


    //method to load reports from .txt file
 /*   public List<ReportConfig> loadReports() {

        List<ReportConfig> reportConfigList = new ArrayList<>();

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("reportData.txt").getFile());
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                ReportConfig reportConfiguration = new ReportConfig();
                List<ColumnConfig> columns = new ArrayList<>();
                String[] sections = input.nextLine().split("\\|");
                //System.out.println("11======="+Arrays.toString(sections));
                reportConfiguration.setReportName(sections[0].split(",")[0]);
                reportConfiguration.setReportType(sections[0].split(",")[1]);

                String[] columnConfigs = sections[1].split(";");
                for (int i = 0; i < columnConfigs.length; i++) {
                    String[] configs = columnConfigs[i].split(",");
                    ColumnConfig columnConfig = new ColumnConfig();
                    columnConfig.setColumnName(configs[0]);
                 /*   columnConfig.setColor(configs[1]);
                    columnConfig.setDataLength(Long.valueOf(configs[2]));
                    columnConfig.setFontStyle(configs[3]);
                    columnConfig.setFontSize(Long.valueOf(configs[4]));*/
         /*           columns.add(columnConfig);
                }
                reportConfiguration.setColumns(columns);
                //System.out.println(reportConfiguration);
                reportConfigList.add(reportConfiguration);
            }
            iReportConfigRepository.saveAll(reportConfigList);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return reportConfigList;
    }*/
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

