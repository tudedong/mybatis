package com.tdd.sqlSession;

import com.tdd.config.BoundSql;
import com.tdd.pojo.Configuration;
import com.tdd.pojo.MappedStatement;

import java.sql.PreparedStatement;

/**
 * @author tudedong
 * @description
 * @date 2020-04-25 15:07:32
 */
public interface PreparedStatementHandler {

    public PreparedStatement doHandler(Configuration configuration, MappedStatement mappedStatement, BoundSql boundSql, Object... params) throws Exception;
}
