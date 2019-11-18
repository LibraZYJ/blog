package com.niit.web.blog.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @author yujie_zhao
 * @ClassName Province
 * @Description 省，level为1，
 * @Date 2019/11/17
 * @Version 1.0
 **/
@Data
public class Province {
    private String name;
    private String level;
    private String code;
    private List<City> cities;
}
