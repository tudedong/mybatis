package com.tdd.sqlSession;

/**
 * @author tudedong
 * @description 工厂类：生产sqlSession:会话对象
 * @date 2020-04-21 21:59:46
 */
public interface SqlSessionFactory {

    public SqlSession openSession();

}
