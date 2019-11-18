package com.niit.web.blog.service;

import com.niit.web.blog.util.Result;

/**
 * @author yujie_zhao
 * @ClassName TopicService
 * @Description TODO
 * @Date 2019/11/17
 * @Version 1.0
 **/
public interface TopicService {
    /**
     * 获取热门专题
     * @return
     */
    Result getHotTopics();

    /**
     * 分页查询专题
     * @param currentPage
     * @param pageCount
     * @return
     */

     Result getPageTopics(int currentPage, int pageCount);
    /**
     * 根据id获取专题详情
     * @param id
     * @return
     */
    Result getTopic(long id);

}
