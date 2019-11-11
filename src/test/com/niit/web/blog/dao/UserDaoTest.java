package com.niit.web.blog.dao;

import org.junit.Test;
import com.niit.web.blog.entity.User;
import com.niit.web.blog.factory.DaoFactory;
import com.niit.web.blog.util.JSoupSpider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

import static org.junit.Assert.*;


public class UserDaoTest {
    private UserDao userDao = DaoFactory.getUserDaoInstance();
    private static Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

    @Test
    public void insert() {
        int n = 0;
        User user = new User();
        user.setMobile("13811112222");
        user.setPassword("123");
        try {
            n = userDao.insert(user);
        } catch (SQLException e) {
            logger.error("新增用户出现异常");
        }
        assertEquals(1, n);
    }

    @Test
    public void batchInsert() {
        try {
            int[] result = userDao.batchInsert(JSoupSpider.getUsers());
            if (result.length != 0) {
                logger.info("成功新增" + result.length + "个用户");
            }
        } catch (SQLException e) {
            logger.error("批量新增用户出现异常");
        }
    }

    @Test
    public void findUserByMobile() {
        User user = null;
        try {
            user = userDao.findUserByMobile("13978536968");
        } catch (SQLException e) {
            logger.error("根据手机号查询用户出现异常");
        }
        assertNotNull(user);
    }
}