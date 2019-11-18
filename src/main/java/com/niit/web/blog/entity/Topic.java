package com.niit.web.blog.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author yujie_zhao
 * @ClassName Topic
 * @Description TODO
 * @Date 2019/11/15
 * @Version 1.0
 **/
@Data
public class Topic {
    private Long id;
    //管理员id
    private Long adminId;
    private String name;
    private String logo;
    private String description;
    private Integer articles;
    private Integer follows;
    private LocalDateTime createTime;
}
