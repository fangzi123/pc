package com.micro.rent.biz.enum_;

public enum OrderStatus {
    NORMAL("正常"), INVALID("失效"), CANCLE("取消");
    private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
