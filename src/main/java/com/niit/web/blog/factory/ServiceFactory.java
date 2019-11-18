package com.niit.web.blog.factory;
import com.niit.web.blog.service.ArticleService;
import com.niit.web.blog.service.TopicService;
import com.niit.web.blog.service.UserService;
import com.niit.web.blog.service.impl.ArticleServiceImpl;
import com.niit.web.blog.service.impl.TopicServiceImpl;
import com.niit.web.blog.service.impl.UserServiceImpl;

/**
 * @author zhaoyujie
 * @ClassName ServiceFactory
 * @Description Service工厂类
 * @Date 10:56 2019/11/7
 * @Version 1.0
 **/
public class ServiceFactory {
    public static UserService getUserServiceInstance() {
        return new UserServiceImpl();
    }

    public static ArticleService getArticleServiceInstance() {
        return new ArticleServiceImpl();
    }
    public static TopicService getTopicServiceInstance() { return new TopicServiceImpl(); }
}

