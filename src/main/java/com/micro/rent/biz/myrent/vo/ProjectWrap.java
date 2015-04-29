package com.micro.rent.biz.myrent.vo;

import com.micro.rent.dbaccess.entity.myrent.ThousePic;
import com.micro.rent.dbaccess.entity.myrent.ThouseTraffic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProjectWrap {
    private String projectId;
    private String communityName;
    private String picture;
    private List<ThouseTraffic> trafficList;
    private String address;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;
    private int count;

    private String houseId;
    private String pre = "/img/";
    private List<String> oneProOneHousesPic;

    public ProjectWrap() {
    }

//	public void format(List<TprojectPic> picList, List<TrafficVo> pTrafficList, List<ThousePic> houPicList) {
//
//		trafficList = new ArrayList<ThouseTraffic>();
//		oneProOneHousesPic = new ArrayList<String>();
//		int size = picList.size();
//		// for(int i=0;i<size;i++){
//		// pictureList.add(picList.get(i).getPicture());
//		// }
//		if (size > 0) {
//			size = new Random().nextInt(size);
//			TprojectPic pic = picList.get(size);
//			picture = pre + pic.getPicture();
//			oneProOneHousesPic.add(picture);
//		}
//		if(houPicList.size()>0){
//			for(int i = 0;i<houPicList.size();i++){
//				oneProOneHousesPic.add(pre+houPicList.get(i).getPicture());
//			}
//		}
//		size = pTrafficList.size();
//		if (size > 0) {
//			TrafficVo tvo = pTrafficList.get(0);
//			communityName = tvo.getCommunityName();
//			BigDecimal latitude = tvo.getLatitude();
//			BigDecimal longitude = tvo.getLongitude();
//			address = BaiduMapAPI.geocoderReverse(latitude, longitude).get(
//					"address");
//		}
//
//		for (TrafficVo vo : pTrafficList) {
//			ThouseTraffic hTraffic = new ThouseTraffic();
//			hTraffic.setDistance(vo.getDistance());
//			hTraffic.setTrafficStation(vo.getTrafficStation());
//			trafficList.add(hTraffic);
//		}
//
//	}

    public void setImg(String proPic, List<ThousePic> houPicList) {
        oneProOneHousesPic = new ArrayList<String>();
        oneProOneHousesPic.add(pre + proPic);
        int size = houPicList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                oneProOneHousesPic.add(pre + houPicList.get(i).getPicture());
            }
        }
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<ThouseTraffic> getTrafficList() {
        return trafficList;
    }

    public void setTrafficList(List<ThouseTraffic> trafficList) {
        this.trafficList = trafficList;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public List<String> getOneProOneHousesPic() {
        return oneProOneHousesPic;
    }

    public void setOneProOneHousesPic(List<String> oneProOneHousesPic) {
        this.oneProOneHousesPic = oneProOneHousesPic;
    }

}
