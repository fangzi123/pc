package com.micro.rent.biz.personal.service;

import com.micro.rent.biz.personal.vo.HouseVo;
import com.micro.rent.dbaccess.entity.personal.Order;

import java.util.List;

/**
 * @author dell
 * @version TODO
 * @Description:
 * @date 2014-9-9
 */
public interface OrderService {

    void addPreOrder(Order order);

    List<Order> queryOrderList(String weixinId);

    /**
     * 个人中心 预约
     *
     * @param weixinId
     * @return
     */
    List<HouseVo> queryOrderHouseList(String weixinId);

    Order queryOneOrderByHouseIdAndWeixinId(String houseId, String weixinId);

    /**
     * 根据指定微信用户、指定日期、指定房源查询预约信息
     *
     * @param weixinId  微信ID
     * @param houseId   房源ID
     * @param orderDate 预约日期
     * @return 预约信息件数
     */
    int queryOrderCountByHouseIdAndWeixinIdAndOrderDate(String weixinId, String houseId, String orderDate);

    boolean hasOrdered(String weixinId, String houseId);
}
