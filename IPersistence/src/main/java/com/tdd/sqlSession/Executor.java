package com.tdd.sqlSession;

import com.tdd.pojo.Configuration;
import com.tdd.pojo.MappedStatement;

import java.util.List;

/**
 * @author tudedong
 * @description
 * @date 2020-04-21 23:08:42
 */
public interface Executor {

    /**
     * 查询
     * @param configuration
     * @param mappedStatement
     * @param params
     * @param <E>
     * @return
     * @throws Exception
     */
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

    /**
     * 插入
     * @param configuration
     * @param mappedStatement
     * @param params
     * @return
     * @throws Exception
     */
    public int update(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;
}
