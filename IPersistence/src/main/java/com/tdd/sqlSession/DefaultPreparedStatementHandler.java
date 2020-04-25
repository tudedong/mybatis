package com.tdd.sqlSession;

import com.mysql.jdbc.StringUtils;
import com.tdd.config.BoundSql;
import com.tdd.pojo.Configuration;
import com.tdd.pojo.MappedStatement;
import com.tdd.utils.ParameterMapping;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * @author tudedong
 * @description
 * @date 2020-04-25 15:09:26
 */
public class DefaultPreparedStatementHandler implements PreparedStatementHandler {
    @Override
    public PreparedStatement doHandler(Configuration configuration, MappedStatement mappedStatement, BoundSql boundSql, Object... params) throws Exception {

        // 3.获取预处理对象：preparedStatement
        PreparedStatement preparedStatement = configuration.getDataSource().getConnection().prepareStatement(boundSql.getSqlText());

        // 4. 设置参数
        //获取到了参数的全路径
        String paramterType = mappedStatement.getParamterType();

        if (StringUtils.isNullOrEmpty(paramterType)) {
            return preparedStatement;
        }

        Class<?> paramtertypeClass = Class.forName(paramterType);
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();

        if (isPrimitive(paramtertypeClass)) {
            // 如果传入参数是基本数据类型，或者基本数据类型的封装类型就不用通过反射设置参数
            preparedStatement.setObject(1, params[0]);
        } else {
            for (int i = 0; i < parameterMappingList.size(); i++) {
                ParameterMapping parameterMapping = parameterMappingList.get(i);
                String content = parameterMapping.getContent();
                //反射
                Field declaredField = paramtertypeClass.getDeclaredField(content);
                //暴力访问
                declaredField.setAccessible(true);
                Object o = declaredField.get(params[0]);
                preparedStatement.setObject(i + 1, o);

            }
        }
        return preparedStatement;
    }

    /**
     * 判断是否基本数据类型，或者基本数据类型的封装类型
     */
    private boolean isPrimitive(Class<?> paramType) {
        if (paramType.isPrimitive()
                || paramType == String.class
                || paramType == Byte.class
                || paramType == Short.class
                || paramType == Boolean.class
                || paramType == Character.class
                || paramType == Integer.class
                || paramType == Long.class
                || paramType == Float.class
                || paramType == Double.class) {
            return true;
        }
        return false;
    }
}
