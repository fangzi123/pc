package com.micro.rent.dbaccess.entity.myrent;

public class TProjectWithBLOBs extends TProject {
    private byte[] picture;

    private byte[] video;

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public byte[] getVideo() {
        return video;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }
}