package com.micro.rent.biz.enum_;

public enum OrderType {
    PREORDER("预约"), RESERVE("预订");
    private String value;

    OrderType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
