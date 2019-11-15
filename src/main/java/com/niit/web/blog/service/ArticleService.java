package com.niit.web.blog.service;
import com.niit.web.blog.entity.Article;

import java.util.List;
import java.util.Map;

/**
 * @author yujie_zhao
 * @ClassName ArticleService
 * @Description TODO
 * @Date 2019/11/14
 * @Version 1.0
 **/
public interface ArticleService {
    /**
     * 用户登录功能
     * @param
     * @return
     */
    List<Article> ListArticle();
}
