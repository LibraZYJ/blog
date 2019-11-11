package com.niit.web.blog.factory;

import com.niit.web.blog.dao.ArticleDao;
import com.niit.web.blog.dao.UserDao;
import com.niit.web.blog.dao.imp.ArticleDaoImpl;
import com.niit.web.blog.dao.imp.UserDaoImpl;


/**
 * @author yujie_zhao
 * @ClassName DaoFactory
 * @Description Dao工厂类
 * @Date 2019/11/5
 * @Version 1.0
 **/
public class DaoFactory {
    public static UserDao getUserDaoInstance(){
        return new UserDaoImpl();
    }
    public static ArticleDao getArticleInstance(){
        return new ArticleDaoImpl();
    }
}
