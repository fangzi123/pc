package com.micro.rent.biz.personal.service;

import com.micro.rent.dbaccess.entity.personal.Trade;

/**
 * 预订
 *
 * @author liqianxi
 * @date 2014-12-29
 */
public interface TradeService {

    /**
     * 预订
     *
     * @param tradeInfo 订单信息
     * @param forTest   测试用标识
     * @return 订单号
     */
    String addTradeInfo(Trade tradeInfo, boolean forTest);

    /**
     * 更新订单状态
     *
     * @param tradeInfo 订单状态
     * @return 更新件数
     */
    int updateTradeStatus(Trade tradeInfo);

    /**
     * 根据订单号查询订单信息
     *
     * @param tradeNo 订单号
     * @return 订单信息
     */
    Trade selectTradeInfoByTradeNo(String tradeNo);

    /**
     * 查询订单信息
     *
     * @param condition 查询条件
     * @return 订单信息
     */
    Trade selectTradeInfo(Trade condition);
}
