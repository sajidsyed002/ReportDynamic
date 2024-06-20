package com.report.config.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class ColumnConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long columnId;
    private String columnName;
    private String color;
    private Long dataLength;
    private String fontStyle;
    private Long fontSize;

    public Long getColumnId() {
        return columnId;
    }

    public void setColumnId(Long columnId) {
        this.columnId = columnId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getDataLength() {
        return dataLength;
    }

    public void setDataLength(Long dataLength) {
        this.dataLength = dataLength;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public Long getFontSize() {
        return fontSize;
    }

    public void setFontSize(Long fontSize) {
        this.fontSize = fontSize;
    }


    @Override
    public int hashCode() {
        return Objects.hash(color, columnId, columnName, dataLength, fontSize, fontStyle);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ColumnConfiguration other = (ColumnConfiguration) obj;
        return Objects.equals(color, other.color) && Objects.equals(columnId, other.columnId)
                && Objects.equals(columnName, other.columnName) && Objects.equals(dataLength, other.dataLength)
                && Objects.equals(fontSize, other.fontSize) && Objects.equals(fontStyle, other.fontStyle)
                ;
    }

    public ColumnConfiguration(Long columnId, String columnName, String color, Long dataLength, String fontStyle,
                               Long fontSize) {
        super();
        this.columnId = columnId;
        this.columnName = columnName;
        this.color = color;
        this.dataLength = dataLength;
        this.fontStyle = fontStyle;
        this.fontSize = fontSize;
    }

    public ColumnConfiguration() {

    }

    @Override
    public String toString() {
        return "ColumnConfiguration{" +
                "columnId='" + columnId + '\'' +
                ", columnName='" + columnName + '\'' +
                ", color='" + color + '\'' +
                ", dataLength=" + dataLength +
                ", fontStyle='" + fontStyle + '\'' +
                ", fontSize=" + fontSize +
                '}';
    }
}
