package com.micro.rent.dbaccess.entity.myrent;

public class ThousePic {
    private String tHousePicId;
    private String houseId;
    private String picture;

    public String gettHousePicId() {
        return tHousePicId;
    }

    public void settHousePicId(String tHousePicId) {
        this.tHousePicId = tHousePicId;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
