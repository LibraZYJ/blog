package com.niit.web.blog.dao;

import com.niit.web.blog.domain.vo.TopicVo;
import com.niit.web.blog.entity.Topic;

import java.sql.SQLException;
import java.util.List;

/**
 * @author yujie_zhao
 * @ClassName Topic
 * @Description TODO
 * @Date 2019/11/15
 * @Version 1.0
 **/
public interface TopicDao {
    /**
     * 批量新增专题
     *
     * @param topicList
     * @return
     * @throws SQLException
     */
    int[] batchInsert(List<Topic> topicList) throws SQLException;

    /**
     * 获取热门专题
     * @return
     * @throws SQLException
     */
    List<Topic> selectHotTopics() throws SQLException;


    /**
     * 分页显示专题信息
     * @param currrentPage
     * @pageCount
     * @return
     * @throws SQLException
     */
    List<Topic> selectByPage(int currrentPage,int pageCount) throws SQLException;

    /**
     * 根据id获取专题详情
     * @param id
     * @return
     * @throws SQLException
     */
    TopicVo getTopic(long id)throws SQLException;
}
