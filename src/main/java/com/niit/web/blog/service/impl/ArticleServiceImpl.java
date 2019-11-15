package com.niit.web.blog.service.impl;

import com.niit.web.blog.dao.ArticleDao;
import com.niit.web.blog.entity.Article;
import com.niit.web.blog.factory.DaoFactory;
import com.niit.web.blog.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.util.List;


/**
 * @author yujie_zhao
 * @ClassName ArticleServiceImpl
 * @Description TODO
 * @Date 2019/11/14
 * @Version 1.0
 **/
public class ArticleServiceImpl implements ArticleService {
    private ArticleDao articleDao = DaoFactory.getArticleInstance();
    private Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    @Override
    public List<Article> ListArticle() {
        List<Article> articleList = null;
        try {
            articleList = articleDao.selectAll();
        } catch (SQLException e) {
            logger.error("查询书出现异常");
        }
        return articleList;
    }

}

