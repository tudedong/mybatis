package com.tdd.pojo;

/**
 * @author tudedong
 * @description 对应*mapper.xml的解析类
 * @date 2020-04-21 21:38:39
 */
public class MappedStatement {

    public final static String SELECT = "SELECT";
    public final static String UPDATE = "UPDATE";
    public final static String DELETE = "DELETE";
    public final static String INSERT = "INSERT";

    /**
     * id标识
     */
    private String id;
    /**
     * 返回值类型
     */
    private String resultType;
    /**
     * 参数值类型
     */
    private String paramterType;
    /**
     * sql语句
     */
    private String sql;

    /**
     * SQL 语句类型
     */
    private String sqlType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParamterType() {
        return paramterType;
    }

    public void setParamterType(String paramterType) {
        this.paramterType = paramterType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }
}
