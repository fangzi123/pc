package com.micro.rent.biz.personal.service.impl;

import com.micro.rent.biz.bean.EOrderType;
import com.micro.rent.biz.map.service.MapService;
import com.micro.rent.biz.personal.service.OrderService;
import com.micro.rent.biz.personal.vo.HouseVo;
import com.micro.rent.common.support.Identities;
import com.micro.rent.dbaccess.dao.myrent.ThousePicDao;
import com.micro.rent.dbaccess.dao.personal.MyOrderDao;
import com.micro.rent.dbaccess.entity.myrent.ThousePic;
import com.micro.rent.dbaccess.entity.personal.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * @author dell
 * @version TODO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2014-9-9
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private MyOrderDao orderDao;

    @Autowired
    private MapService baiduMapService;

    @Autowired
    private ThousePicDao tHousePicDao;
    private transient Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void addPreOrder(Order order) {
        order.settOrderId(Identities.create32LenUUID());
        order.setCreateTime(new Date());
        order.setOrderType(EOrderType.ORDER.getCode());
        order.setStatus("1");
        log.info("预约信息[orderId:" + order.gettOrderId() + ",weixinId" + order.getWeixinId()
                + ",projectId" + order.getProjectId() + ",houseId" + order.getHouseId()
                + ",orderDate:" + order.getOrderDate() + ",telephone" + order.getTelephone()
                + ",createTime:" + order.getCreateTime() + "]");
        orderDao.addPreOrder(order);
    }

    @Override
    public Order queryOneOrderByHouseIdAndWeixinId(String houseId,
                                                   String weixinId) {
        Order order = null;
        List<Order> orderList = orderDao.queryOrderByHouseIdAndWeixinId(
                houseId, weixinId);
        if (orderList != null && orderList.size() > 0) {
            // TODO 取一条
            order = orderList.get(0);
        }
        return order;
    }

    @Override
    public List<Order> queryOrderList(String weixinId) {
        List<Order> result = orderDao.queryOrderList(weixinId);
        if (result == null || result.isEmpty())
            return new ArrayList<Order>();
        return result;
    }

    @Override
    public List<HouseVo> queryOrderHouseList(String weixinId) {
//		MapPoint mapPoint= (MapPoint) LocationEventMessageHandler.lrumap
//				.get(weixinId);
//		if(mapPoint==null){
//			return null;
//		}
        List<HouseVo> resultList = orderDao.queryOrderHouseList(weixinId);
        for (HouseVo house : resultList) {
            String picture = findHousePicByHouseId(house.getHouseId());
            house.setPicture(picture);
            //setDuration(mapPoint, house);
        }
        return resultList;
    }

    public String findHousePicByHouseId(String houseId) {
        List<ThousePic> picList = tHousePicDao
                .selectHousePicListByHouseId(houseId);
        int size = picList.size();
        String picture = "";
        if (size > 0) {
            size = new Random().nextInt(size);
            picture = picList.get(size).getPicture();
        }
        return picture;
    }

//	private void setDuration(MapPoint mapPoint, HouseVo hvo) {
//		BigDecimal lat = mapPoint.getLat();
//		BigDecimal lng = mapPoint.getLng();
//		BigDecimal wpLat = hvo.getLatitude();
//		BigDecimal wpLng = hvo.getLongitude();
//		RequestParam reqParam = new RequestParam();
//		reqParam.setMode(ETranfficType.DRIVING.getCode());
//		reqParam.setOrigin(lat + "," + lng);
//		reqParam.setDestination(wpLat + "," + wpLng);
//		// reqParam.setRegion("上海市");
//		reqParam.setMode("1");
//		reqParam.setOrigin_region("北京");
//		reqParam.setDestination_region("北京");
//		String duration = baiduMapService.doLeastTimeBetweenTwoPoint(reqParam);
//	//	duration = String.valueOf(Math.ceil(Double.valueOf(duration) / 60));
//        duration = DateUtil.secondsToHourMinute(Long.parseLong(duration));
//		hvo.setDuration(duration);
//	}

    @Override
    public boolean hasOrdered(String weixinId, String houseId) {
        boolean exist = false;
        Order order = queryOneOrderByHouseIdAndWeixinId(houseId, weixinId);
        if (order != null) {
            exist = true;
        }
        return exist;
    }

    /**
     * 根据指定微信用户、指定日期、指定房源查询预约信息
     *
     * @param weixinId  微信ID
     * @param houseId   房源ID
     * @param orderDate 预约日期
     * @return 预约信息件数
     */
    @Override
    public int queryOrderCountByHouseIdAndWeixinIdAndOrderDate(String weixinId, String houseId, String orderDate) {
        return orderDao.queryOrderCountByHouseIdAndWeixinIdAndOrderDate(houseId, weixinId, orderDate);
    }

}
