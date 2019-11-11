package com.niit.web.blog.util;

import com.niit.web.blog.entity.Article;
import com.niit.web.blog.entity.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyujie
 * @ClassName JSoupSpider
 * @Description JSoup实现的一个爬虫工具
 * @Date 9:13 2019/11/7
 * @Version 1.0
 **/
public class JSoupSpider {
    private static Logger logger = LoggerFactory.getLogger(JSoupSpider.class);

    public static List<User> getUsers() {
        Document document = null;
        List<User> userList = new ArrayList<>(100);
        for (int i = 1; i <= 3; i++) {
            try {
                document = Jsoup.connect("https://www.jianshu.com/recommendations/users?utm_source=desktop&utm_medium=index-users&page=" + i).get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            Elements divs = document.getElementsByClass("col-xs-8");
            divs.forEach(div -> {
                Element wrapDiv = div.child(0);
                Element link = wrapDiv.child(0);
                Elements linkChildren = link.children();
                User user = new User();
                user.setMobile(UserDataUtil.getMobile());
                user.setPassword(UserDataUtil.getPassword());
                user.setGender(UserDataUtil.getGender());
                user.setAvatar("https:" + linkChildren.get(0).attr("src"));
                user.setNickname(linkChildren.get(1).text());
                user.setIntroduction(linkChildren.get(2).text());
                user.setBirthday(UserDataUtil.getBirthday());
                user.setCreateTime(LocalDateTime.now());
                userList.add(user);
            });
        }
        return userList;
    }
    public static List<Article> getArticle() {
        Document document = null;
        List<Article> articleList = new ArrayList<>(100);

            try {
                document = Jsoup.connect("https://www.jianshu.com/").get();
            } catch (IOException e) {
                logger.error("获取文章连接异常");
            }
            Elements divs = document.getElementsByClass("have-img");
            divs.forEach(div -> {
                Element wrapImg = div.child(0);
                Element contentDiv = div.child(1);
                Element account = contentDiv.child(2);
                Article article = new Article();
                article.setAuthorId(ArticleDataUtil.getAuthorId());
                article.setHeadLine(UserDataUtil.getGender());
                article.setContent(contentDiv.child(1).text());
                article.setAvatar("https:" + wrapImg.child(0).attr("src"));
                article.setLikeAccount(ArticleDataUtil.getLikeAccount());
                article.setCommentAccount(ArticleDataUtil.getCommentAccount());
                article.setCreateTime(LocalDateTime.now());
                articleList.add(article);
            });

        return articleList;
    }
}
