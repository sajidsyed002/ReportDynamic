package com.report.config.service;

import com.report.config.dto.ReportDataDTO;
import com.report.config.entity.ColumnConfig;
import com.report.config.entity.ReportConfig;
import com.report.config.repository.IReportConfigRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataService {

    @Autowired
    private IReportConfigRepository iReportConfigRepository;

    @Autowired
    private EntityManager entityManager;

    //to fetch Hedge data for a report
    public ResponseEntity<ReportDataDTO> fetchReportWithData(Long reportId) throws Exception{
        Optional<ReportConfig> reportConfigOpt = iReportConfigRepository.findById(reportId);
        if (reportConfigOpt.isPresent()) {
            return fetchData(reportConfigOpt.get());
        }
        return new ResponseEntity<>(new ReportDataDTO(), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ReportDataDTO> fetchData(ReportConfig reportConfiguration) throws Exception{

        StringBuilder builder = new StringBuilder();
        ReportDataDTO reportDataDTO = new ReportDataDTO();
        builder.append("SELECT ");
        List<ColumnConfig> list = reportConfiguration.getColumns();
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

        reportDataDTO.setReportId(reportConfiguration.getReportId());
        reportDataDTO.setReportName(reportConfiguration.getReportName());
        reportDataDTO.setReportType(reportConfiguration.getReportType());
        reportDataDTO.setData(listMap);

        return new ResponseEntity<>(reportDataDTO, HttpStatus.OK);
    }
}
