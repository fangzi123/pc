package com.micro.rent.biz.map.baidu;

public enum EResultCodeStatus {

    SUCCESS(0),
    FAIL_1(1);

    private int code;

    EResultCodeStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
