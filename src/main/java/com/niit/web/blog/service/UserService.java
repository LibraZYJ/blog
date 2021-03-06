package com.niit.web.blog.service;

import com.niit.web.blog.domain.dto.UserDto;
import com.niit.web.blog.entity.Article;
import com.niit.web.blog.entity.User;
import com.niit.web.blog.util.Result;

import java.util.List;
import java.util.Map;

/**
 * @author yujie_zhao
 * @ClassName StudentService
 * @Description TODO
 * @Date 2019/11/5
 * @Version 1.0
 **/
public interface UserService {
    /**
     * 用户登录功能
     * @param userDto
     * @return
     */
    Map<String,Object> signIn(UserDto userDto);

     List<User> ListUser();

    /**
     * 获取分页用户信息
     * @return
     */
    Result getPageUsers();
    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    User findUserById(long id);
    /**
     * 获取热门用户信息
     * @return
     */
    Result getHotUsers();
}
