package com.niit.web.blog.factory;
import com.niit.web.blog.service.UserService;
import com.niit.web.blog.service.impl.UserServiceImpl;

/**
 * @author mq_xu
 * @ClassName ServiceFactory
 * @Description Service工厂类
 * @Date 10:56 2019/11/7
 * @Version 1.0
 **/
public class ServiceFactory {
    public static UserService getUserServiceInstance() {
        return new UserServiceImpl();
    }
}

