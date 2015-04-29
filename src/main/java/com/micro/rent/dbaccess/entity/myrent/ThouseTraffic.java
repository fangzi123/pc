package com.micro.rent.dbaccess.entity.myrent;

import java.math.BigDecimal;

public class ThouseTraffic {
    private String tHouseTrafficId;
    private String projectId;
    private String trafficType;
    private String trafficName;
    private String trafficStation;
    private BigDecimal distance;

    public String gettHouseTrafficId() {
        return tHouseTrafficId;
    }

    public void settHouseTrafficId(String tHouseTrafficId) {
        this.tHouseTrafficId = tHouseTrafficId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(String trafficType) {
        this.trafficType = trafficType;
    }

    public String getTrafficName() {
        return trafficName;
    }

    public void setTrafficName(String trafficName) {
        this.trafficName = trafficName;
    }

    public String getTrafficStation() {
        return trafficStation;
    }

    public void setTrafficStation(String trafficStation) {
        this.trafficStation = trafficStation;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }
}
