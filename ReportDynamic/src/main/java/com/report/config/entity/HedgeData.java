package com.report.config.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class HedgeData {

    @Id
    private String id;
    private String fundName;
    private String manager;
    private String managerContact;
    @Nullable
    private String strategy;
    private String geoFocus;
    private Long fundInception;
    private Long managerFounded;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGeoFocus(String geoFocus) {
        this.geoFocus = geoFocus;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManagerContact() {
        return managerContact;
    }

    public void setManagerContact(String managerContact) {
        this.managerContact = managerContact;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getGeoFocus() {
        return geoFocus;
    }

    public void setGeoSize(String geoFocus) {
        this.geoFocus = geoFocus;
    }

    public Long getFundInception() {
        return fundInception;
    }

    public void setFundInception(Long fundInception) {
        this.fundInception = fundInception;
    }

    public Long getManagerFounded() {
        return managerFounded;
    }

    public void setManagerFounded(Long managerFounded) {
        this.managerFounded = managerFounded;
    }

    public HedgeData(String id, String fundName, String manager, String managerContact, String strategy, String geoFocus, Long fundInception, Long managerFounded) {
        this.id = id;
        this.fundName = fundName;
        this.manager = manager;
        this.managerContact = managerContact;
        this.strategy = strategy;
        this.geoFocus = geoFocus;
        this.fundInception = fundInception;
        this.managerFounded = managerFounded;
    }

    public HedgeData() {

    }
}
