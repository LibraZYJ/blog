package com.niit.web.blog.dao.imp;

import com.niit.web.blog.dao.AddressDao;
import com.niit.web.blog.entity.Address;
import com.niit.web.blog.util.DbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author yujie_zhao
 * @ClassName AddressDaoImpl
 * @Description TODO
 * @Date 2019/11/18
 * @Version 1.0
 **/
public class AddressDaoImpl implements AddressDao {
    private static Logger logger = LoggerFactory.getLogger(AddressDaoImpl.class);
    @Override
    public int[] batchInsert(List<Address> addressList) throws SQLException {
        Connection connection  = DbUtil.getConnection();
        String sql = "INSERT INTO t_address(address) VALUE (?)";
        connection.setAutoCommit(false);
        PreparedStatement pstmt = connection.prepareStatement(sql);
        addressList.forEach(address -> {
            try {
                pstmt.setString(1,address.getAddress());
                pstmt.addBatch();
            } catch (SQLException e) {
                logger.error("批量加入地址数据出现异常");
            }
        });
        int[] result = pstmt.executeBatch();
        //别忘记提交
        connection.commit();
        pstmt.close();
        connection.close();
        return result;
    }
}
