package com.niit.web.blog.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * @author yujie_zhao
 * @ClassName Article
 * @Description 文章实体类
 * @Date 2019/11/9
 * @Version 1.0
 **/
@Data
public class Article {
    private Long id;
    private Integer authorId;
    private String title;
    private String content;
    private String avatar;
    private Integer likeAccount;
    private Integer commentAccount;;
    private LocalDateTime createTime;

}
