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
     * 删除user
     * @param user
     * @return
     */
    int delete(User user);
}
