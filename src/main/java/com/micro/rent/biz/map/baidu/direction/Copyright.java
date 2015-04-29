package com.micro.rent.biz.map.baidu.direction;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class Copyright {

    private String text;
    private String imageUrl;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
