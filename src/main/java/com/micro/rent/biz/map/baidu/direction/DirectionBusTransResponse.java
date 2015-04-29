package com.micro.rent.biz.map.baidu.direction;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

@XStreamAlias("DirectionBusTransResponse")
public class DirectionBusTransResponse {

    private int status;
    private String message;
    private int type;

    private CopyrightInfo info;

    @XStreamAlias("result")
    private DirectionResult result;

    public CopyrightInfo getInfo() {
        return info;
    }

    public void setInfo(CopyrightInfo info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public DirectionResult getResult() {
        return result;
    }

    public void setResult(DirectionResult result) {
        this.result = result;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

