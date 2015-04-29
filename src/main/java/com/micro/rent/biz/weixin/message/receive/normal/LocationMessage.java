package com.micro.rent.biz.weixin.message.receive.normal;

import java.math.BigDecimal;

public class LocationMessage extends BaseOrdinaryMessage {

    private BigDecimal locationX;

    private BigDecimal locationY;

    private int scale;

    private String label;

    public BigDecimal getLocationX() {
        return locationX;
    }

    public void setLocationX(BigDecimal locationX) {
        this.locationX = locationX;
    }

    public BigDecimal getLocationY() {
        return locationY;
    }

    public void setLocationY(BigDecimal locationY) {
        this.locationY = locationY;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


}
