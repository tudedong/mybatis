package com.tdd.sqlSession;

import com.tdd.pojo.Configuration;

/**
 * @author tudedong
 * @description
 * @date 2020-04-21 22:45:26
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
