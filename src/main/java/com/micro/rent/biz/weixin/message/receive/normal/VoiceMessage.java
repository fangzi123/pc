package com.micro.rent.biz.weixin.message.receive.normal;

import com.micro.rent.biz.weixin.message.BaseMessage;

public class VoiceMessage extends BaseMessage {

    private String mediaId;

    private String format;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }


}
