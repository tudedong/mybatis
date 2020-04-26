package com.tdd.dao;

import com.tdd.pojo.User;

import java.util.List;

/**
 * @author tudedong
 * @description
 * @date 2020-04-21 23:56:40
 */
public interface IUserDao {
    /**
     * 查询user列表
     * @return
     */
    List<User> findAll();

    /**
     * 查询单个user
     * @param user
     * @return
     */
    User selectOne(User user);

    /**
     * 创建user
     */
    int create(User user);

    /**
     * 更新user
     * @param user
     * @return
     */
    int update(User user);

    /**
     * 根据id删除user
     * @param id
     * @return
     */
    int delete(long id);
}
