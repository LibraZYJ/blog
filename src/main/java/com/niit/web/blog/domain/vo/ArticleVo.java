package com.niit.web.blog.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author yujie_zhao
 * @ClassName ArticleVo
 * @Description TODO
 * @Date 2019/11/18
 * @Version 1.0
 **/
@Data
public class ArticleVo {
    private Long id;
    private Long authorId;
    private String nickname;
    private String avatar;
    private String title;
    private String description;
    private String content;
    private Integer commentAccount;
    private Integer likeAccount;
    private Integer forwardAccount;
    private LocalDateTime createTime;
}