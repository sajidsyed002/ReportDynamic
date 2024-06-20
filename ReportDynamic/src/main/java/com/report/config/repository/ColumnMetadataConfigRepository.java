package com.report.config.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnMetadataConfigRepository {

    @Autowired
    private EntityManager entityManager;

    public List<Object[]> getColumnMetadataByColumnId(Long id){
        Query query = entityManager.createQuery("SELECT B.metadataName, A.metadataValue " +
                "FROM COLUMN_METADATA_CONFIG A JOIN METADATA_CONFIG B ON B.metadataId = A.metadataId " +
                "WHERE A.columnId =:ID");
        query.setParameter("ID",id);

        return query.getResultList();
    }
}
