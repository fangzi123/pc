package com.micro.rent.pc.entity;

import java.math.BigDecimal;
/**
 * @ClassName: HousePriceRange
 * @Description:所有房源的最低价格和最高价格
 * @author: wff
 * @date: 2015年2月5日 下午6:08:39
 */
public class HousePriceRange {
    private Integer id;
    private BigDecimal minPrice;// 最低价;
    private BigDecimal maxPrice;// 最高价;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public BigDecimal getMinPrice() {
        return minPrice;
    }
    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }
    public BigDecimal getMaxPrice() {
        return maxPrice;
    }
    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

}
