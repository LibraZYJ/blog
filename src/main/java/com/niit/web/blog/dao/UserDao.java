package com.niit.web.blog.dao;

import com.niit.web.blog.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author mq_xu
 * @ClassName UserDao
 * @Description TODO
 * @Date 10:54 2019/11/9
 * @Version 1.0
 **/
public interface UserDao {
    /**
     * 查询所有信息
     * @return
     * @throws SQLException
     */
    List<User> selectAll() throws SQLException;
    /**
     * 新增用户
     * @param user
     * @return
     * @throws SQLException
     */
    int insert(User user) throws SQLException;

    /**
     * 批量新增用户
     *
     * @param userList
     * @return int[]
     * @throws SQLException
     */
    int[] batchInsert(List<User> userList) throws SQLException;

    /**
     * 根据手机号查找用户
     * @param mobile
     * @return
     * @throws SQLException
     */
    User findUserByMobile(String mobile) throws SQLException;
    /**
     * 通过id查询用户
     * @param id
     * @return
     * @throws SQLException
     */
    User getUserById (long id) throws SQLException;
}
