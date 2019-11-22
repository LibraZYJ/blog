package com.niit.web.blog.domain.vo;

import com.niit.web.blog.domain.dto.SimpleUser;
import com.niit.web.blog.entity.Article;
import com.niit.web.blog.entity.Topic;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author yujie_zhao
 * @ClassName TopicVo
 * @Description 专题视图类, 包含专题自身信息、专题下所有文章、创建者信息(简略)、关注者信息(简略)
 * @Date 2019/11/17
 * @Version 1.0
 **/@Data
public class TopicVo {
    private Long id;
    private Long adminId;
    private String topicName;
    private String logo;
    private String description;
    private Integer articles;
    private Integer follows;
    private LocalDateTime createTime;
    private List<ArticleVo> articleList;
    private SimpleUser simpleUser;
    private List<SimpleUser> simpleUsers;

}
