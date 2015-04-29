package com.micro.rent.dbaccess.dao.personal;

import com.micro.rent.dbaccess.entity.personal.Trade;

/**
 * 订单DAO
 *
 * @author liqianxi
 * @date 2014-12-29
 */
public interface TradeDao {

    /**
     * 生成订单
     *
     * @param tradeInfo 订单信息
     */
    void insertTrade(Trade tradeInfo);

    /**
     * 更新订单状态
     *
     * @param tradeInfo 订单状态信息
     */
    int updateTradeStatus(Trade tradeInfo);

    /**
     * 查询订单信息
     *
     * @param condition 查询条件
     */
    Trade selectTradeInfo(Trade condition);

    /**
     * 根据订单号查询订单信息
     *
     * @param tradeNo 订单号
     */
    Trade selectTradeInfoByTradeNo(String tradeNo);

}
