package com.niit.web.blog.util;

import com.niit.web.blog.entity.Address;
import com.niit.web.blog.entity.Article;
import com.niit.web.blog.entity.Topic;
import com.niit.web.blog.entity.User;
import org.jsoup.Connection;
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

    /**
     *爬取用户信息数据
     * @return
     */
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
    public static List<User> getUsersSite() {
        Document document = null;
        List<User> usersSite = new ArrayList<>(100);

            try {
                document = Jsoup.connect("http://www.ip33.com/area_code.html").get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            Elements divs = document.getElementsByClass("ip");
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
                usersSite.add(user);
            });
        return usersSite;
    }

    /**
     * 爬取文章详情数据
     * @return
     */
    public static List<Article> getArticle() {
        Document document = null;
        List<Article> articleList = new ArrayList<>(100);

        for (int i = 1; i < 10; i++) {
            try {
                document = Jsoup.connect("https://www.jianshu.com/c/87b50a03a96e?order_by=top&count=50&page=" + i).get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            Elements divs = document.getElementsByClass("have-img");
            divs.forEach(div -> {
                String articleUrl = div.child(0).attr("href");
                Document document1 = null;
                try {
                    document1 = Jsoup.connect("https://www.jianshu.com" + articleUrl).get();
                } catch (IOException e) {
                    logger.error("连接失败");
                }
                Element articleElement = document1.getElementsByClass("_2rhmJa").first();
                Article article = new Article();
                article.setContent(articleElement.html());

                Elements elements = div.children();
                Element linkElement = elements.get(0);
                Element divElement = elements.get(1);
                article.setAuthorId(ArticleDataUtil.getAuthorId());
                article.setTitle(divElement.child(0).text());
                article.setSketch(divElement.child(1).text());
                String img = "https:" + linkElement.child(0).attr("src");
                int index = img.indexOf("?");
                article.setAvatar(img.substring(0, index));
                Elements metaChildren = divElement.child(2).children();
                String comments = metaChildren.get(2).text();
                String likes = metaChildren.last().text();
                try {
                    article.setCommentAccount(Integer.parseInt(comments));
                    article.setLikeAccount(Integer.parseInt(likes));
                } catch (NumberFormatException e) {
                    logger.error("格式转换异常");
                }
                article.setCreateTime(ArticleDataUtil.getCreateTime());
                articleList.add(article);

            });
        }
        return articleList;
    }
    /**
     * 爬取简书网的热门专题
     *
     * @return
     */
    public static List<Topic> getTopics() {
        List<Topic> topicList = new ArrayList<>(100);
        Connection connection;
        Document document = null;
        for (int i = 1; i <= 3; i++) {
            try {
                //分析页面得到url和惨
                connection = Jsoup.connect("https://www.jianshu.com/recommendations/collections?order_by=hot&page=" + i);
                //通过chrome开发者工具查看该请求必须添加请求头
                connection.header("X-PJAX", "true");
                document = connection.get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            assert document != null;
            Elements list = document.select(".collection-wrap");
            list.forEach(item -> {
                Elements elements = item.children();
                Topic topic = new Topic();
                Element link = elements.select("a").get(0);
                Element logo = link.child(0);
                Element name = link.child(1);
                Element description = link.child(2);
                Element articles = elements.select(".count > a").get(0);
                Element follows = elements.select(".count > a").get(0);
                topic.setAdminId(DataUtil.getUserId());
                topic.setName(name.text());
                topic.setLogo(logo.attr("src"));
                topic.setDescription(description.text());
                String[] str = StringUtil.getDigital(articles.text());
                topic.setArticles(Integer.parseInt(str[0]));
                str = StringUtil.getDigital(follows.text());
                topic.setFollows(Integer.parseInt(str[0]));
                topic.setCreateTime(DataUtil.getCreateTime());
                topicList.add(topic);
            });

        }
        return topicList;
    }

    /**
     * 爬取地址数据
     * @param
     */
    public static List<Address> getAddress (){
        List<Address> addressList = new ArrayList<>();
        Document document = null;
        try {
            document = Jsoup.connect("http://www.ip33.com/area_code.html").get();
        } catch (IOException e) {
            logger.error("获取地址连接异常");
        }
        Elements divs = document.getElementsByClass("ip");
        divs.forEach(div -> {
            Elements elements = div.child(1).child(0).child(1).children();
            elements.forEach(element -> {
                Address address = new Address();
                StringBuilder province = new StringBuilder("");
                String country = div.child(1).child(0).child(0).text();
                int c = country.indexOf(" ");
                int p = div.child(0).text().indexOf(" ");
                int e = element.text().indexOf(" ");
                province.append(div.child(0).text().substring(0, p)).append(country.substring(0, c)).append(element.text().substring(0, e));
                address.setAddress(province.toString());
                addressList.add(address);
            });

        });

        return addressList;
    }
    public static void main(String[] args) {
//        List<Topic> topicList = JSoupSpider.getTopics();
//        System.out.println(topicList.size());
        List<Address> addressList = JSoupSpider.getAddress();
        System.out.println(addressList.size());
    }
}
