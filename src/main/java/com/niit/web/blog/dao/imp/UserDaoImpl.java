package com.niit.web.blog.dao.imp;

import com.niit.web.blog.dao.UserDao;
import com.niit.web.blog.entity.Article;
import com.niit.web.blog.entity.User;
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
 * @author Tao
 * @ClassName UserDaoImpl
 * @Description 用户接口实现
 * @Date 2019/11/9
 * @Version 1.0
 **/
public class UserDaoImpl implements UserDao {
    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public List<User> selectAll() throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_user ORDER BY id DESC";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<User> userList = new ArrayList<>();
        while (rs.next()){
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setMobile(rs.getString("mobile"));
            user.setPassword(rs.getString("password"));
            user.setNickname(rs.getString("nickname"));
            user.setAvatar(rs.getString("avatar"));
            user.setGender(rs.getString("gender"));
            user.setBirthday(rs.getDate("birthday").toLocalDate());
            user.setIntroduction(rs.getString("introduction"));
            user.setAddress(rs.getString("address"));
            user.setFollows(rs.getShort("follows"));
            user.setFans(rs.getShort("fans"));
            user.setArticles(rs.getShort("articles"));
            user.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            user.setStatus(rs.getShort("status"));
            userList.add(user);
        }
        return userList;
    }

    @Override
    public int insert(User user) throws SQLException {
        return 0;
    }

    @Override
    public int[] batchInsert(List<User> userList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO t_user (mobile,password,nickname,avatar,gender,birthday,introduction,create_time) VALUES (?,?,?,?,?,?,?,?) ";

        PreparedStatement pstmt = connection.prepareStatement(sql);
        userList.forEach(user -> {
            try {
                pstmt.setString(1, user.getMobile());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getNickname());
                pstmt.setString(4, user.getAvatar());
                pstmt.setString(5, user.getGender());
                //日期的设置，可以使用setObject
                pstmt.setObject(6, user.getBirthday());
                pstmt.setString(7, user.getIntroduction());
                pstmt.setObject(8, user.getCreateTime());
                pstmt.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        int[] result = pstmt.executeBatch();
        //别忘记提交
        connection.commit();
        pstmt.close();
        connection.close();
        return result;
    }

    @Override
    public User findUserByMobile(String mobile) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_user WHERE mobile = ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,mobile);
        ResultSet rs = pstmt.executeQuery();
        User user = null;
        if (rs.next()){
            user = new User();
            user.setId(rs.getLong("id"));
            user.setMobile(rs.getString("mobile"));
            user.setPassword(rs.getString("password"));
            user.setNickname(rs.getString("nickname"));
            user.setAvatar(rs.getString("avatar"));
            user.setGender(rs.getString("gender"));
            user.setBirthday(rs.getDate("birthday").toLocalDate());
            user.setIntroduction(rs.getString("introduction"));
            user.setAddress(rs.getString("address"));
            user.setFollows(rs.getShort("follows"));
            user.setFans(rs.getShort("fans"));
            user.setArticles(rs.getShort("articles"));
            user.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            user.setStatus(rs.getShort("status"));
        }
        return user;
    }
//    public User findUserByMobile(String mobile) throws SQLException {
//        Connection connection = DbUtil.getConnection();
//        String sql = "SELECT * FROM t_user WHERE mobile = ? ";
//        PreparedStatement pstmt = connection.prepareStatement(sql);
//        pstmt.setString(1, mobile);
//        ResultSet rs = pstmt.executeQuery();
//        return convertUser(rs).get(0);
//    }
//    private List<User> convertUser(ResultSet rs) {
//        List<User> userList = new ArrayList<>(50);
//        try {
//            while (rs.next()) {
//                User user = new User();
//                user.setId(rs.getLong("id"));
//                user.setMobile(rs.getString("mobile"));
//                user.setPassword(rs.getString("password"));
//                user.setNickname(rs.getString("nickname"));
//                user.setAvatar(rs.getString("avatar"));
//                user.setGender(rs.getString("gender"));
//                user.setBirthday(rs.getDate("birthday").toLocalDate());
//                user.setIntroduction(rs.getString("introduction"));
////                user.setAddress(rs.getString("address"));
//                user.setFollows(rs.getShort("follows"));
//                user.setFans(rs.getShort("fans"));
//                user.setArticles(rs.getShort("articles"));
//                user.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
//                user.setStatus(rs.getShort("status"));
//                userList.add(user);
//            }
//        } catch (SQLException e) {
//            logger.error("查询用户数据产生异常");
//        }
//        return userList;
//    }
/*通过id查询用户*/
    @Override
    public User getUserById(long id) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_user WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setLong(1,id);
        ResultSet rs = pstmt.executeQuery();
        User user = null;
        while (rs.next()){
            user = new User();
            user.setId(rs.getLong("id"));
            user.setMobile(rs.getString("mobile"));
            user.setPassword(rs.getString("password"));
            user.setNickname(rs.getString("nickname"));
            user.setAvatar(rs.getString("avatar"));
            user.setGender(rs.getString("gender"));
            user.setBirthday(rs.getDate("birthday").toLocalDate());
            user.setIntroduction(rs.getString("introduction"));
            user.setAddress(rs.getString("address"));
            user.setFollows(rs.getShort("follows"));
            user.setFans(rs.getShort("fans"));
            user.setArticles(rs.getShort("articles"));
            user.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            user.setStatus(rs.getShort("status"));
        }
        return user;
    }

}
