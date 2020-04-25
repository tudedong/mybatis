package com.tdd.sqlSession;

import java.util.List;

/**
 * @author tudedong
 * @description 定义会话对象SqlSession方法
 * @date 2020-04-21 22:49:56
 */
public interface SqlSession {

    /**
     * 查询所有
     *
     * @param statementId
     * @param params
     * @param <E>
     * @return
     * @throws Exception
     */
    public <E> List<E> selectList(String statementId, Object... params) throws Exception;

    /**
     * 根据条件查询单个
     *
     * @param statementId
     * @param params
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T selectOne(String statementId, Object... params) throws Exception;


    /**
     * 插入一条记录
     * @param statementId
     * @param params
     * @return
     * @throws Exception
     */
    public int insert(String statementId, Object... params) throws Exception;

    /**
     * 更新一条记录
     * @param statementId
     * @param params
     * @return
     * @throws Exception
     */
    public int update(String statementId, Object... params)throws Exception;

    /**
     * 删除一条数据
     * @param statementId
     * @param params
     * @return
     * @throws Exception
     */
    public int delete(String statementId, Object... params)throws Exception;

    /**
     * 为Dao接口生成代理实现类
     *
     * @param mapperClass
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<?> mapperClass);

}
