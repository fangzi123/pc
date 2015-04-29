package com.micro.rent.biz.map.baidu.direction;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class CopyrightInfo {

    private Copyright copyright;

    public Copyright getCopyright() {
        return copyright;
    }

    public void setCopyright(Copyright copyright) {
        this.copyright = copyright;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
