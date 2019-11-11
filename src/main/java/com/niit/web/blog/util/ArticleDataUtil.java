package com.niit.web.blog.util;

import java.time.LocalDate;
import java.util.Random;

import static java.lang.Integer.parseInt;

/**
 * @author yujie_zhao
 * @ClassName ArticleDataUtil
 * @Description 数据生成工具，用来生成文章数据
 * @Date 2019/11/9
 * @Version 1.0
 **/
public class ArticleDataUtil {
    /**
     * 获得电话号码
     * @return
     */
    public static Integer getAuthorId (){
        Random random = new Random();
        StringBuilder rs = new StringBuilder();
        rs.append(random.nextInt(73));

        return Integer.valueOf(rs.toString());
    }
    public static Integer getLikeAccount (){
        Random random = new Random();
        StringBuilder rs = new StringBuilder();
        rs.append(random.nextInt(100000));

        return Integer.valueOf(rs.toString());
    }

    public static Integer getCommentAccount(){

        Random random = new Random();
        StringBuilder rs = new StringBuilder();
        rs.append(random.nextInt(100));

        return Integer.valueOf(rs.toString());
    }

    public static String getGender(){
        String[] genders = new String[]{"男","女"};
        Random random = new Random();
        int index = random.nextInt(2);
        return genders[index];
    }

    public static LocalDate getCreateTime(){
        LocalDate now = LocalDate.now();
        Random random = new Random();
        int bound = random.nextInt(8888);

        return now.minusDays(bound);
    }


    public static void main(String[] args) {
        System.out.println(getAuthorId());
    }
}
