package com.micro.rent.biz.map.baidu.geocoder;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

@XStreamAlias("GeocoderSearchResponse")
public class GeocoderSearchResponse {
    private int status;
    private Result result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}

