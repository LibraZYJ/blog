package com.niit.web.blog.service.impl;

import com.niit.web.blog.dao.UserDao;
import com.niit.web.blog.domain.dto.UserDto;
import com.niit.web.blog.entity.Article;
import com.niit.web.blog.entity.User;
import com.niit.web.blog.factory.DaoFactory;
import com.niit.web.blog.service.UserService;
import com.niit.web.blog.util.Md5Util;
import com.niit.web.blog.util.Message;
import com.niit.web.blog.util.Result;
import com.niit.web.blog.util.ResultCode;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userDao = DaoFactory.getUserDaoInstance();
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public Map<String, Object> signIn(UserDto userDto) {
        User user = null;
        Map<String, Object> map = new HashMap<>();
        try {
//            调用Dao实现类中的方法查询数据，将查到的数据存入user中
            user = userDao.findUserByMobile(userDto.getMobile());
        } catch (SQLException e) {
            logger.error("根据手机号查询用户出现异常");
        }
        if(user != null){
//            将前端获得的密码与user中的密码进行比较
            if(user.getPassword().equals(Md5Util.crypt(userDto.getPassword()))){
//                将数据存入不同键的对应值中
                map.put("msg", Message.SIGN_IN_SUCCESS);
                map.put("data", user);
            }else {
                map.put("msg", Message.PASSWORD_ERROR);
            }
        }else {
            map.put("msg", Message.MOBILE_NOT_FOUND);
        }
//        返回map，供controller使用
        return map;
    }

    @Override
    public List<User> ListUser() {
        List<User> userList = null;
        try {
            userList = userDao.selectAll();
        } catch (SQLException e) {
            logger.error("查所有用户出现异常");
        }
        return userList;
    }

    @Override
    public Result getPageUsers() {
        return null;
    }

    @Override
    public User findUserById(long id) {
        User user = null;
        try {
            user = userDao.getUserById(id);
        } catch (SQLException e) {
            logger.error("通过id查询用户出现异常");        }
        return user;
    }

    @Override
    public Result getHotUsers() {
        List<User> userList = null;
        try {
            userList = userDao.selectHotUsers();
        } catch (SQLException e) {
            logger.error("获取热门用户出现异常");
        }
        if (userList != null) {
            //成功并返回数据
            return Result.success(userList);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }
}
