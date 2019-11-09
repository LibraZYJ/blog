package com.niit.web.blog.domain;

import lombok.Data;

/**
 * @author yujie_zhao
 * @ClassName UserDao
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
@Data
public class UserDto {
    private String mobile;
    private  String password;

    public UserDto(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }
    public UserDto() {
    }

}
