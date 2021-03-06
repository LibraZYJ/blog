package com.niit.web.blog.dao.imp;
import com.niit.web.blog.dao.ArticleDao;
import com.niit.web.blog.domain.vo.ArticleVo;
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
            article.setSketch(rs.getString("sketch"));
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
        String sql = "INSERT INTO t_article(authorId, title,sketch,content, avatar, likeAccount, commentAccount, createTime) VALUES (?,?,?,?,?,?,?,?)";
        connection.setAutoCommit(false);
        PreparedStatement pstmt = connection.prepareStatement(sql);
        articleList.forEach(article -> {
            try {
                pstmt.setInt(1,article.getAuthorId());
                pstmt.setString(2,article.getTitle());
                pstmt.setString(3,article.getSketch());
                pstmt.setString(4,article.getContent());
                pstmt.setString(5,article.getAvatar());
                pstmt.setInt(6,article.getLikeAccount());
                pstmt.setInt(7,article.getCommentAccount());
                pstmt.setObject(8,article.getCreateTime());
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

    @Override
    public List<ArticleVo> selectAuthorArticle(long id) throws SQLException {
        List<ArticleVo> articleVoList = new ArrayList<>(20);
        Connection connection = DbUtil.getConnection();
        /*在文章表和用户表联查，得到结视图对象*/
        String sql = "SELECT a.id, a.authorId, a.title, a.sketch, b.avatar, content,a.createTime, a.likeAccount,a.commentAccount, b.id, b.nickname, b.avatar\n" +
                "FROM t_article a\n" +
                "LEFT JOIN t_user b\n" +
                "ON a.authorId = b.id\n" +
                "WHERE a.authorId = ?\n"+
                /* ORDER BY:对结果集进行排序。  DESC：降序  asc:升序  Limit：检索数据的数量limit(a,b) a:从a+1开始检索，b:检索的最大长达*/
                "ORDER BY a.authorId DESC LIMIT 20";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        /*对sql语句中的问号进行赋值*/
        pstmt.setLong(1,id);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            ArticleVo articleVo = new ArticleVo();
            articleVo.setId(rs.getLong("id"));
            articleVo.setAuthorId(rs.getLong("authorId"));
            articleVo.setTitle(rs.getString("title"));
            articleVo.setDescription(rs.getString("sketch"));
            articleVo.setAvatar(rs.getString("avatar"));
            articleVo.setContent(rs.getString("content"));
            articleVo.setCreateTime(rs.getTimestamp("createTime").toLocalDateTime());
            articleVo.setCommentAccount(rs.getInt("likeAccount"));
            articleVo.setCommentAccount(rs.getInt("commentAccount"));
            articleVo.setNickname(rs.getString("nickname"));
            articleVoList.add(articleVo);
        }
        return articleVoList;
    }


}
