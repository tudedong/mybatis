package com.tdd.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tudedong
 * @description 对应sqlMapConfig.xml的解析类
 * @date 2020-04-21 21:43:22
 */
public class Configuration {

    /**
     * 数据源信息
     */
    private DataSource dataSource;

    /**
     * 多个MappedStatement信息，key：statementId  value:封装好的MappedStatement对象
     */
    Map<String,MappedStatement> mappedStatementMap = new HashMap<String,MappedStatement>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatementMap() {
        return mappedStatementMap;
    }

    public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
        this.mappedStatementMap = mappedStatementMap;
    }
}
