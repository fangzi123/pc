package com.micro.rent.biz.weixin.message.receive.normal;


public class ImageMessage extends BaseOrdinaryMessage {

    private String picUrl;

    private String mediaId;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }


}
