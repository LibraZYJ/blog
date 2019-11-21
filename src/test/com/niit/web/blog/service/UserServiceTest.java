package com.niit.web.blog.service;

import com.niit.web.blog.util.Result;
import org.junit.Test;
import com.niit.web.blog.domain.dto.UserDto;
import com.niit.web.blog.factory.ServiceFactory;

import java.util.Map;

public class UserServiceTest {
    private UserService userService = ServiceFactory.getUserServiceInstance();

    @Test
    public void signIn() {
        /*创建Dto对象*/
        UserDto userDto = new UserDto();
        userDto.setMobile("13906567215");
        userDto.setPassword("111");
        /*创建map对象获取signIn的返回值（msg,data)*/
        Map<String, Object> map = userService.signIn(userDto);
        System.out.println(map);
    }
    @Test
    public void getHotUsers() {
        Result result = userService.getHotUsers();
        System.out.println(result);
    }
}