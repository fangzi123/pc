package com.micro.rent.pc.entity.vo;

import java.util.List;

/**
 * @ClassName: MapResultVo
 * @Description: 地图页返回的json
 * @author: wff
 * @date: 2015年2月2日 下午2:50:28
 */
public class MapResultVo {
    private String url;//房源详情url
    private String markFlg;// 根据不同的标识生成不同的覆盖物
    private List<Mark> markList;// 覆盖物相关信息数组
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getMarkFlg() {
        return markFlg;
    }
    public void setMarkFlg(String markFlg) {
        this.markFlg = markFlg;
    }
    public List<Mark> getMarkList() {
        return markList;
    }
    public void setMarkList(List<Mark> markList) {
        this.markList = markList;
    }
    
}
