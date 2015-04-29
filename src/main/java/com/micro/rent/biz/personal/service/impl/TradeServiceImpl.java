package com.micro.rent.biz.personal.service.impl;

import com.micro.rent.biz.enum_.ETradeStatus;
import com.micro.rent.biz.personal.service.TradeService;
import com.micro.rent.common.support.Identities;
import com.micro.rent.dbaccess.dao.personal.TradeDao;
import com.micro.rent.dbaccess.entity.personal.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 预订
 *
 * @author liqianxi
 * @date 2014-12-29
 */
@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TradeDao tradeDao;

    /**
     * 预订
     *
     * @param tradeInfo 订单信息
     * @param forTest   测试用标识
     * @return 订单号
     */
    public String addTradeInfo(Trade tradeInfo, boolean forTest) {
        long currentTime = System.currentTimeMillis() / 1000;
        // 订单号（10位随机数字）
        tradeInfo.setTradeNo(forTest ? ("test" + Identities.randomString(10, true)) : Identities.randomString(10, true));
        // 未支付
        tradeInfo.setStatus(ETradeStatus.UNPAY.getCode());
        tradeInfo.setCreateTime(currentTime);
        tradeInfo.setUpdateTime(currentTime);
        tradeDao.insertTrade(tradeInfo);
        return tradeInfo.getTradeNo();
    }

    /**
     * 更新订单状态
     *
     * @param tradeInfo 订单状态
     * @return 更新件数
     */
    public int updateTradeStatus(Trade tradeInfo) {
        tradeInfo.setUpdateTime(System.currentTimeMillis() / 1000);
        return tradeDao.updateTradeStatus(tradeInfo);
    }

    /**
     * 根据订单号查询订单信息
     *
     * @param tradeNo 订单号
     * @return 订单信息
     */
    public Trade selectTradeInfoByTradeNo(String tradeNo) {
        return tradeDao.selectTradeInfoByTradeNo(tradeNo);
    }

    /**
     * 查询订单信息
     *
     * @param condition 查询条件
     * @return 订单信息
     */
    public Trade selectTradeInfo(Trade condition) {
        return tradeDao.selectTradeInfo(condition);
    }
}
