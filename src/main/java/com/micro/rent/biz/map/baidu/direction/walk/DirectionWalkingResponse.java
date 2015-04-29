package com.micro.rent.biz.map.baidu.direction.walk;

import com.micro.rent.biz.map.baidu.direction.CopyrightInfo;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author dell
 * @Description:
 * @date 2014-8-5
 */
@XStreamAlias("DirectionWalkingResponse")
public class DirectionWalkingResponse {

    private int status;
    private String message;
    private int type;
    private CopyrightInfo info;
    private Result result;

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

    public CopyrightInfo getInfo() {
        return info;
    }

    public void setInfo(CopyrightInfo info) {
        this.info = info;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
