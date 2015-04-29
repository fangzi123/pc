package com.micro.rent.dbaccess.entity.aboutus;

public class Corp {

    private String tCorpId;
    private String corpName;
    private String corpAddr;
    private String representative;
    private String corpPhone;
    private String corpDesc;

    public String gettCorpId() {
        return tCorpId;
    }

    public void settCorpId(String tCorpId) {
        this.tCorpId = tCorpId;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getCorpAddr() {
        return corpAddr;
    }

    public void setCorpAddr(String corpAddr) {
        this.corpAddr = corpAddr;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getCorpPhone() {
        return corpPhone;
    }

    public void setCorpPhone(String corpPhone) {
        this.corpPhone = corpPhone;
    }

    public String getCorpDesc() {
        return corpDesc;
    }

    public void setCorpDesc(String corpDesc) {
        this.corpDesc = corpDesc;
    }

}
