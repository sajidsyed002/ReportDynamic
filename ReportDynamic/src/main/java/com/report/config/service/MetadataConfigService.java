package com.report.config.service;

import com.report.config.dto.ColumnMetadataDTO;
import com.report.config.dto.ReportConfigDTO;
import com.report.config.entity.ColumnConfig;
import com.report.config.entity.ColumnMetadataConfig;
import com.report.config.entity.ReportConfig;
import com.report.config.repository.ColumnMetadataConfigRepository;
import com.report.config.repository.IColumnMetadataConfigRepository;
import com.report.config.repository.IReportConfigRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class MetadataConfigService {

    @Autowired
    private IReportConfigRepository iReportConfigRepository;

    @Autowired
    private ColumnMetadataConfigRepository columnMetadataConfigRepository;

    @Autowired
    private IColumnMetadataConfigRepository iColumnMetadataConfigRepository;

    @Autowired
    private EntityManager entityManager;

    //to save/insert data into all tables from reportData.txt file
    public List<ReportConfig> saveReportWithMetadata() {

        List<ReportConfig> reportConfigList = new ArrayList<>();
        List<ColumnMetadataConfig> columnMetadataConfigList = new ArrayList<>();

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("reportData.txt").getFile());
            Scanner input = new Scanner(file);
            Long columnId = 1L;
            while (input.hasNextLine()) {
                ReportConfig reportConfiguration = new ReportConfig();
                List<ColumnConfig> columnConfigList = new ArrayList<>();
                String[] sections = input.nextLine().split("\\|");
                System.out.println("11=======" + Arrays.toString(sections));
                reportConfiguration.setReportName(sections[0].split(",")[0]);
                reportConfiguration.setReportType(sections[0].split(",")[1]);

                if (sections.length > 1) {
                    String[] columnConfigs = sections[1].split(";");
                    for (int i = 0; i < columnConfigs.length; i++) {
                        String[] configs = columnConfigs[i].split(",");
                        ColumnConfig columnConfig = new ColumnConfig();
                        columnConfig.setColumnName(configs[0]);
                        columnConfigList.add(columnConfig);

                        for (int j = 1; j < configs.length; j = j + 2) {
                            ColumnMetadataConfig columnMetadataConfig = new ColumnMetadataConfig();
                            columnMetadataConfig.setColumnId(columnId);
                            columnMetadataConfig.setMetadataId(Long.valueOf(configs[j]));
                            columnMetadataConfig.setMetadataValue(configs[j + 1]);
                            //columnMetadataConfigRepository.save(columnMetadataConfig);
                            columnMetadataConfigList.add(columnMetadataConfig);
                            System.out.println(" ======= " + columnMetadataConfig);
                        }
                        columnId++;
                    }

                    reportConfiguration.setColumns(columnConfigList);
                }
                    System.out.println(reportConfiguration);
                    reportConfigList.add(reportConfiguration);

            }
            iReportConfigRepository.saveAll(reportConfigList);
            iColumnMetadataConfigRepository.saveAll(columnMetadataConfigList);
            input.close();

        }catch (PersistenceException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return reportConfigList;
    }

    //to fetch report with metadata configs
    public ResponseEntity<ReportConfigDTO> fetchReportWithColumnMetadata(Long reportId) throws Exception{
        Optional<ReportConfig> reportConfigOpt = iReportConfigRepository.findById(reportId);
        ReportConfigDTO reportConfigDTO = new ReportConfigDTO();
        if (reportConfigOpt.isPresent()) {
            reportConfigDTO.setReportId(reportId);
            reportConfigDTO.setReportName(reportConfigOpt.get().getReportName());
            reportConfigDTO.setReportType(reportConfigOpt.get().getReportType());

            List<ColumnMetadataDTO> columnMetadataDTOList = new ArrayList<>();
            for(ColumnConfig columnConfig : reportConfigOpt.get().getColumns()){
                ColumnMetadataDTO columnMetadataDTO = new ColumnMetadataDTO();
                columnMetadataDTO.setColumnName(columnConfig.getColumnName());
                columnMetadataDTO.setMetadataConfigs(fetchColumnMetadata(columnConfig.getColumnId()));
                columnMetadataDTOList.add(columnMetadataDTO);
            }

            reportConfigDTO.setColumnConfigs(columnMetadataDTOList);
            return new ResponseEntity<>(reportConfigDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(reportConfigDTO, HttpStatus.NOT_FOUND);

    }

    public HashMap<String, String> fetchColumnMetadata(Long columnId){
        HashMap<String, String> map = new HashMap<>();
        List<Object[]> metadataConfigsList = columnMetadataConfigRepository.getColumnMetadataByColumnId(columnId);
        for(Object[] row : metadataConfigsList){
            System.out.println(" ==row=="+Arrays.toString(row));
            map.put(row[0].toString(),row[1].toString());
        }
        return map;
    }




  /*  public List<ColumnMetadataConfig> getColumnMetadata(Long columnId) {
     // return columnMetadataConfigRepository.findByIdColumnId(columnId);
    }*/

  /*  public Optional<ColumnConfig> getColumnById(Long columnId) {
        return columnConfigRepository.findById(columnId);
    }*/
}
