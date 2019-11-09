package com.niit.web.blog.service;

import org.junit.Test;
import com.niit.web.blog.dao.UserDao;
import com.niit.web.blog.domain.UserDto;
import com.niit.web.blog.factory.ServiceFactory;
import org.junit.Test;

import java.util.Map;
import static org.junit.Assert.*;

public class UserServiceTest {
    private UserService userService = ServiceFactory.getUserServiceInstance();

    @Test
    public void signIn() {
        UserDto userDto = new UserDto("13986647663", "690f585c2386bd41cc826af6627d5c11");
        Map<String, Object> map = userService.signIn(userDto);
        System.out.println(map);
    }
}