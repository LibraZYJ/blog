package com.niit.web.blog.dao;

import com.niit.web.blog.entity.Article;
import com.niit.web.blog.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author yujie_zhao
 *
 * @ClassName Article
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
public interface ArticleDao {
    /**
     * 查询所有信息
     * @return
     * @throws SQLException
     */
    List<Article> selectAll() throws SQLException;
    /**
     * 批量新增用户
     *
     * @param articleList
     * @return int[]
     * @throws SQLException
     */
    int[] batchInsert(List<Article> articleList) throws SQLException;
}
