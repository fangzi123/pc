package com.micro.rent.pc.entity.vo;

import java.util.List;

import com.micro.rent.pc.entity.Branch;
import com.micro.rent.pc.entity.Brand;

public class BrandVo extends Brand{
    private String pic;
    private String rank;//推荐权重
    private Branch branch;
    private List branchList;
    public String getPic() {
        return pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
    public Branch getBranch() {
        return branch;
    }
    public void setBranch(Branch branch) {
        this.branch = branch;
    }
    public List getBranchList() {
        return branchList;
    }
    public void setBranchList(List branchList) {
        this.branchList = branchList;
    }
}
