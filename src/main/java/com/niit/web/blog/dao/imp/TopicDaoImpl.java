package com.niit.web.blog.dao.imp;

import com.niit.web.blog.dao.TopicDao;
import com.niit.web.blog.domain.vo.TopicVo;
import com.niit.web.blog.entity.Topic;
import com.niit.web.blog.util.DbUtil;
import jdk.jfr.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yujie_zhao
 * @ClassName TopicDao
 * @Description TODO
 * @Date 2019/11/17
 * @Version 1.0
 **/
public class TopicDaoImpl implements TopicDao {
    private static Logger logger = LoggerFactory.getLogger(TopicDaoImpl.class);

    @Override
    public int[] batchInsert(List<Topic> topicList) throws SQLException {
        Connection connection  = DbUtil.getConnection();
        String sql = "INSERT INTO t_topic (admin_id , name , logo , description,articles,follows,create_time ) VALUES (?,?,?,?,?,?,?)";
        connection.setAutoCommit(false);
        PreparedStatement pstmt = connection.prepareStatement(sql);
        topicList.forEach(topic ->{
            try {
                pstmt.setLong(1,topic.getAdminId());
                pstmt.setString(2,topic.getName());
                pstmt.setString(3,topic.getLogo());
                pstmt.setString(4,topic.getDescription());
                pstmt.setInt(5,topic.getArticles());
                pstmt.setInt(6,topic.getFollows());
                pstmt.setObject(7,topic.getCreateTime());
                pstmt.addBatch();

            } catch (SQLException e) {
                logger.error("批量加入专题数据产生异常");
            }
        });
        int[] result = pstmt.executeBatch();
        connection.commit();
        DbUtil.close(null,pstmt,connection);
        return result;
    }

    @Override
    public List<Topic> selectHotTopics() throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_topic ORDER BY follows DESC LIMIT 10";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        List<Topic> topicList = convert(rs);
        DbUtil.close(rs,pst, connection);

        return topicList;
    }

    @Override
    public List<Topic> selectByPage(int currrentPage, int pageCount) throws SQLException {
        return null;
    }

    @Override
    public TopicVo getTopic(long id) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT a.*,b.nickname,b.avatar"+
                "FROM t_topic a"+
                "LEFT JOIN t_user b"+
                "ON a.admin_id = b.id"+
                "WHERE a.id = ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1,id);
        ResultSet rs = pst.executeQuery();
        TopicVo topicVo =null;
        if (rs.next()){
            topicVo = new TopicVo();
            topicVo.setId(rs.getLong("id"));
            topicVo.setAdminId((rs.getLong("admin_id")));
            topicVo.setTopicName(rs.getString("topic_name"));
            topicVo.setLogo(rs.getString("logo"));
            topicVo.setDescription(rs.getString("description"));
            topicVo.setArticles(rs.getInt("articles"));
            topicVo.setFollows(rs.getInt("follow"));
            topicVo.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
        }
        DbUtil.close(rs,pst, connection);
        return topicVo;
    }
    private List<Topic> convert(ResultSet rs) {
        List<Topic> topicList = new ArrayList<>(50);
        try {
            while (rs.next()) {
                Topic topic = new Topic();
                topic.setId(rs.getLong("id"));
                topic.setAdminId(rs.getLong("admin_id"));
                topic.setName(rs.getString("name"));
                topic.setLogo(rs.getString("logo"));
                topic.setDescription(rs.getString("description"));
                topic.setArticles(rs.getInt("articles"));
                topic.setFollows(rs.getInt("follows"));
                topic.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
                topicList.add(topic);
            }
        } catch (SQLException e) {
            logger.error("查询专题数据产生异常");
        }
        return topicList;
    }
}
