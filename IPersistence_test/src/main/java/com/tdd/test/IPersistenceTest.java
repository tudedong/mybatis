package com.tdd.test;

import com.tdd.dao.IUserDao;
import com.tdd.io.Resources;
import com.tdd.pojo.User;
import com.tdd.sqlSession.SqlSession;
import com.tdd.sqlSession.SqlSessionFactory;
import com.tdd.sqlSession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;
import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;

/**
 * @author tudedong
 * @description 测试类
 * @date 2020-04-21 21:25:22
 */
public class IPersistenceTest {

    private IUserDao userDao;
    private SqlSession sqlSession;

    @Before
    public void before() throws DocumentException, PropertyVetoException{
        // 1. 读取配置文件，读成字节输入流，注意：现在还没解析，只是存储在内存中
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");

        // 2.1解析配置文件（sqlMapConfig.xml和mapper.xml），封装Configuration对象
        // 2.2利用构建者模式创建了DefaultSqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);

        //3.利用工厂模式生产了DefaultSqlsession实例对象
        //   设置了事务不自动提交  完成了executor对象的创建
        sqlSession = sqlSessionFactory.openSession();

        //4.使用JDK动态代理对mapper接口产生代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }
    /**
     * 测试查询
     */
    @Test
    public void testSelect(){
        //查询全部user信息
        List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }

    }

    /**
     * 测试创建
     */
    @Test
    public void testCreate(){
        User user = new User();
        user.setId(5);
        user.setUsername("tutu1");
        user.setPassword("123");
        user.setBirthday("2020-4-25");
        //5.代理对象调用接口中的任意方法，执行的都是动态代理中的invoke方法
        int result = userDao.create(user);
        System.out.println(result);
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(3);
        user.setUsername("tutu1");
        user.setPassword("123");
        user.setBirthday("2020-4-25");
        int result = userDao.update(user);

        System.out.println(result);
    }
    /**
     * 测试删除
     */
    @Test
    public void testDelete(){
        User user = new User();
        user.setId(3);
        int result = userDao.delete(user);
        System.out.println(result);
    }

}
