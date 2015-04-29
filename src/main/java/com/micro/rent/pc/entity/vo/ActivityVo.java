package com.micro.rent.pc.entity.vo;

import com.micro.rent.pc.entity.Activity;


public class ActivityVo extends Activity {
    
    private String accountId;
    private String nickname;
    private String mobile;
    private String dataFlg;
    
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getDataFlg() {
        return dataFlg;
    }
    public void setDataFlg(String dataFlg) {
        this.dataFlg = dataFlg;
    }
    
}