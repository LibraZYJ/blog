package com.niit.web.blog.dao.imp;
import com.niit.web.blog.dao.ArticleDao;
import com.niit.web.blog.entity.Article;

import com.niit.web.blog.util.DbUtil;
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
 * @ClassName ArticleImpl
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
public class ArticleDaoImpl implements ArticleDao {

    private Logger logger = LoggerFactory.getLogger(ArticleDaoImpl.class);


    @Override
    public List<Article> selectAll() throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_article ORDER BY id DESC";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Article> articleList = new ArrayList<>();
        while (rs.next()){
            Article article = new Article();
            article.setId(rs.getLong("id"));
            article.setAuthorId(rs.getInt("authorId"));
            article.setTitle(rs.getString("title"));
            article.setContent(rs.getString("content"));
            article.setAvatar(rs.getString("avatar"));
            article.setLikeAccount(rs.getInt("likeAccount"));
            article.setCommentAccount(rs.getInt("commentAccount"));
            article.setCreateTime(rs.getTimestamp("createTime").toLocalDateTime());
            articleList.add(article);
        }
        return articleList;
    }

    @Override
    public int[] batchInsert(List<Article> articleList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO t_article(authorId, title, content, avatar, likeAccount, commentAccount, createTime) VALUES (?,?,?,?,?,?,?)";
        connection.setAutoCommit(false);
        PreparedStatement pstmt = connection.prepareStatement(sql);
        articleList.forEach(article -> {
            try {
                pstmt.setInt(1,article.getAuthorId());
                pstmt.setString(2,article.getTitle());
                pstmt.setString(3,article.getContent());
                pstmt.setString(4,article.getAvatar());
                pstmt.setInt(6,article.getLikeAccount());
                pstmt.setInt(5,article.getCommentAccount());
                pstmt.setObject(7,article.getCreateTime());
                pstmt.addBatch();
            } catch (SQLException e) {
                logger.error("文章批量插入出错");
            }
        });
        int [] n = pstmt.executeBatch();
        connection.commit();
        DbUtil.close(null,pstmt,connection);
        return n;
    }


}
