package com.micro.rent.dbaccess.entity.aboutus;

public class Discount {

    private String tDiscountId;
    private String discountDesc;
    private String discountPic;
    private String startDate;
    private String endDate;
    private String status;

    public String gettDiscountId() {
        return tDiscountId;
    }

    public void settDiscountId(String tDiscountId) {
        this.tDiscountId = tDiscountId;
    }

    public String getDiscountDesc() {
        return discountDesc;
    }

    public void setDiscountDesc(String discountDesc) {
        this.discountDesc = discountDesc;
    }

    public String getDiscountPic() {
        return discountPic;
    }

    public void setDiscountPic(String discountPic) {
        this.discountPic = discountPic;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
