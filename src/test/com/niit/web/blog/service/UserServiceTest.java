package com.niit.web.blog.service;

import org.junit.Test;
import com.niit.web.blog.domain.dto.UserDto;
import com.niit.web.blog.factory.ServiceFactory;

import java.util.Map;

public class UserServiceTest {
    private UserService userService = ServiceFactory.getUserServiceInstance();

    @Test
    public void signIn() {
        UserDto userDto = new UserDto("13953833080", "6bb881460abc7d2e7ed6da00bdd515b5");
        Map<String, Object> map = userService.signIn(userDto);
        System.out.println(map);
    }
}