package com.niit.web.blog.domain.dto;

import lombok.Data;

/**
 * @author yujie_zhao
 * @ClassName Address
 * @Description 地址类，由省和市组成
 * @Date 2019/11/17
 * @Version 1.0
 **/
@Data
public class Address {
    private String province;
    private String city;
}
