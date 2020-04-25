package com.tdd.config;

import com.tdd.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tudedong
 * @description
 * @date 2020-04-21 23:20:13
 */
public class BoundSql {

    //解析过后的sql
    private String sqlText;

    private List<ParameterMapping> parameterMappingList = new ArrayList<ParameterMapping>();

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList) {
        this.sqlText = sqlText;
        this.parameterMappingList = parameterMappingList;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}
