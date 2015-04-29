package com.micro.rent.biz.myrent.vo;

import com.micro.rent.biz.map.baidu.MapPoint;

import java.util.ArrayList;
import java.util.List;

public class MatchResultWrap {

    private List<MatchResultVo> lstResult = new ArrayList<MatchResultVo>();

    private MapPoint currPoint;

    private List<MapPoint> lstPoint = new ArrayList<MapPoint>();

    private List<MatchResultWrap2> lstRt = new ArrayList<MatchResultWrap2>();

    private String staticUrl;

    public MatchResultWrap() {
        lstResult = new ArrayList<MatchResultVo>();
    }

    public MatchResultWrap(List<MatchResultVo> lstResult, MapPoint currPoint, String staticUrl) {
        this.lstResult = lstResult;
        this.currPoint = currPoint;
        this.staticUrl = staticUrl;

        lstPoint.add(currPoint);

        MatchResultWrap2 curItem = new MatchResultWrap2();
        curItem.setLng(currPoint.getLng());
        curItem.setLat(currPoint.getLat());
        //curItem.settHouseId("");

        for (MatchResultVo result : lstResult) {
            MapPoint mp = new MapPoint(result.getLongitude(), result.getLatitude());
            lstPoint.add(mp);

            MatchResultWrap2 item = new MatchResultWrap2();
            item.setLng(result.getLongitude());
            item.setLat(result.getLatitude());
            item.settHouseId(result.gettHouseId());
            item.setHouseId(result.getHouseId());
            item.setProjectId(result.getProjectId());
            lstRt.add(item);
        }
    }


    public List<MatchResultVo> getLstResult() {
        return lstResult;
    }

    public void setLstResult(List<MatchResultVo> lstResult) {
        this.lstResult = lstResult;
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

    public List<MatchResultWrap2> getLstRt() {
        return lstRt;
    }

    public void setLstRt(List<MatchResultWrap2> lstRt) {
        this.lstRt = lstRt;
    }

}
