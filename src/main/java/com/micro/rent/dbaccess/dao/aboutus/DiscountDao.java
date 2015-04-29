package com.micro.rent.dbaccess.dao.aboutus;

import com.micro.rent.dbaccess.entity.aboutus.Discount;

import java.util.List;

public interface DiscountDao {

    List<Discount> selectDiscountInfo();
}
