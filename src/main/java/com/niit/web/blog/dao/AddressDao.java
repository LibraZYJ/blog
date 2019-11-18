package com.niit.web.blog.dao;

import com.niit.web.blog.entity.Address;

import java.sql.SQLException;
import java.util.List;

/**
 * @author yujie_zhao
 * @ClassName AddressDao
 * @Description TODO
 * @Date 2019/11/18
 * @Version 1.0
 **/
public interface AddressDao {
    int[] batchInsert(List<Address> addressList) throws SQLException;
}
