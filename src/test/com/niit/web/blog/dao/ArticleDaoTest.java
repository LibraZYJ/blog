package com.niit.web.blog.dao;

import org.junit.Test;
import com.niit.web.blog.factory.DaoFactory;
import com.niit.web.blog.util.JSoupSpider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class ArticleDaoTest {
    private ArticleDao articleDao = DaoFactory.getArticleInstance();
    private Logger logger = LoggerFactory.getLogger(ArticleDaoTest.class);
    @Test
    public void batchInsert() throws SQLException {
        int[] n = articleDao.batchInsert(JSoupSpider.getArticle());
        if(n.length!=0){
            logger.info("获取文章数据成功");
            System.out.println(n.length);
        }else {
            logger.error("获取文章失败");
        }
    }

    @Test
    public void selectAll() {

    }

}