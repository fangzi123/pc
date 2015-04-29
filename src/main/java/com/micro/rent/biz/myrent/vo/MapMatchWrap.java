package com.micro.rent.biz.myrent.vo;

import com.micro.rent.biz.map.baidu.MapPoint;

import java.util.ArrayList;
import java.util.List;

public class MapMatchWrap {

    private List<ProjectMapVo> lstResult = new ArrayList<ProjectMapVo>();

    private MapPoint currPoint;

    private List<MapPoint> lstPoint = new ArrayList<MapPoint>();

    //private List<MatchResultWrap2> lstRt = new ArrayList<MatchResultWrap2>();

    private String staticUrl;

    public MapMatchWrap() {

    }

    public MapMatchWrap(List<ProjectMapVo> lstResult, MapPoint currPoint,
                        String staticUrl) {
        this.lstResult = lstResult;
        this.currPoint = currPoint;
        this.staticUrl = staticUrl;

        lstPoint.add(currPoint);

        MatchResultWrap2 curItem = new MatchResultWrap2();
        curItem.setLng(currPoint.getLng());
        curItem.setLat(currPoint.getLat());
        // curItem.settHouseId("");

        for (ProjectMapVo result : lstResult) {
            MapPoint mp = new MapPoint(result.getLongitude(),
                    result.getLatitude());
            lstPoint.add(mp);

        }
    }

    public MapPoint getCurrPoint() {
        return currPoint;
    }

    public void setCurrPoint(MapPoint currPoint) {
        this.currPoint = currPoint;
    }

    public String getStaticUrl() {
        return staticUrl;
    }

    public void setStaticUrl(String staticUrl) {
        this.staticUrl = staticUrl;
    }

    public List<MapPoint> getLstPoint() {
        return lstPoint;
    }

    public void setLstPoint(List<MapPoint> lstPoint) {
        this.lstPoint = lstPoint;
    }

    public List<ProjectMapVo> getLstResult() {
        return lstResult;
    }

    public void setLstResult(List<ProjectMapVo> lstResult) {
        this.lstResult = lstResult;
    }


}
