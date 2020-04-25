package com.tdd.sqlSession;

import com.tdd.pojo.Configuration;
import com.tdd.pojo.MappedStatement;

import java.lang.reflect.*;
import java.util.List;



/**
 * @author tudedong
 * @description
 * @date 2020-04-21 22:51:37
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {

        //将要去完成对simpleExecutor里的query方法的调用
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        List<Object> list = simpleExecutor.query(configuration, mappedStatement, params);

        return (List<E>) list;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        List<Object> objects = selectList(statementId, params);
        if(objects.size()==1){
            return (T) objects.get(0);
        }else {
            throw new RuntimeException("查询结果为空或者返回结果过多");
        }
    }

    @Override
    public int insert(String statementId, Object... params) throws Exception {
        int count = update(statementId, params);
        return count;
    }

    @Override
    public int update(String statementId, Object... params) throws Exception {
        //将要去完成对simpleExecutor里的update方法的调用
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        int count = simpleExecutor.update(configuration, mappedStatement, params);
        return count;
    }

    @Override
    public int delete(String statementId, Object... params) throws Exception {
        int count = update(statementId, params);
        return count;
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        // 使用JDK动态代理来为Dao接口生成代理对象，并返回
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(),
                new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 底层都还是去执行JDBC代码 //根据不同情况，来调用selctList或者selectOne
                // 准备参数 1：statmentid :sql语句的唯一标识：namespace.id= 接口全限定名.方法名
                // 方法名：findAll
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();
                String statementId = className+"."+methodName;

                // 准备参数2：params:args
                String sqlType = configuration.getMappedStatementMap().get(statementId).getSqlType();
                Object result = null;
                switch (sqlType) {
                    case MappedStatement.SELECT: {
                        // 获取被调用方法的返回值类型
                        Type genericReturnType = method.getGenericReturnType();
                        // 判断是否进行了 泛型类型参数化
                        if (genericReturnType instanceof ParameterizedType) {
                            result = selectList(statementId, args);
                        }
                        break;
                    }
                    case MappedStatement.INSERT: {
                        result = insert(statementId,args);
                        break;
                    }
                    case MappedStatement.UPDATE: {
                        result = update(statementId,args);
                        break;
                    }
                    case MappedStatement.DELETE: {
                        result = delete(statementId, args);
                        break;
                    }
                    default:
                        throw new Exception("Unknown execution method for: " + sqlType);
                }
                return result;
            }
        });

        return (T) proxyInstance;
    }
}
