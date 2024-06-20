package com.report.config.service;

import com.report.config.entity_pgsql.ColumnConfigPg;
import com.report.config.entity_pgsql.ReportConfigPg;
import com.report.config.repository_pgsql.IColumnConfigRepositoryPg;
import com.report.config.repository_pgsql.IReportConfigRepositoryPg;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ReportConfigPgsqlService {

    @Autowired
    private IReportConfigRepositoryPg iReportConfigRepositoryPg;

    @Autowired
    private IColumnConfigRepositoryPg iColumnConfigRepositorypg;

    @Autowired
    private EntityManager entityManager;

    // to fetch all the reports along with columns
    public List<ReportConfigPg> fetchReportList() {
        List<ReportConfigPg> reportConfigPgList = iReportConfigRepositoryPg.findAll();
   /*     for (ReportConfigPg reportConfigPg : reportConfigPgList) {
            List<ColumnConfigPg> columnConfigPgList = new ArrayList<>();
            List<Object[]> columnConfigObject = getColumnsByReportId(reportConfigPg.getReportId());
            for (Object[] row : columnConfigObject) {
                ColumnConfigPg columnConfigPg = new ColumnConfigPg();
                columnConfigPg.setColumnId((Long) row[0]);
                columnConfigPg.setColumnName(row[1].toString());
                columnConfigPgList.add(columnConfigPg);
            }
            reportConfigPg.setColumns(columnConfigPgList);
        }*/
        return reportConfigPgList;
    }

   /* public List<Object[]> getColumnsByReportId(Long reportId) {
        Query query = entityManager.createQuery("SELECT A.columnId, A.columnName " +
                "FROM COLUMN_CONFIG A JOIN REPORT_COLUMN_CONFIG B ON A.columnId = B.columnId " +
                "WHERE REPORT_ID =:ID");
        query.setParameter("ID", reportId);

        return query.getResultList();
    }*/
}