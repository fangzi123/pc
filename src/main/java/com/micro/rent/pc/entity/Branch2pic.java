package com.micro.rent.pc.entity;

public class Branch2pic {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.branch2pic.id
     *
     * @mbggenerated Sun Jan 25 12:43:21 CST 2015
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.branch2pic.branch_id
     *
     * @mbggenerated Sun Jan 25 12:43:21 CST 2015
     */
    private Long branchId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.branch2pic.pic_path
     *
     * @mbggenerated Sun Jan 25 12:43:22 CST 2015
     */
    private String picPath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.branch2pic.type
     *
     * @mbggenerated Sun Jan 25 12:43:22 CST 2015
     */
    private Object type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.branch2pic.priority
     *
     * @mbggenerated Sun Jan 25 12:43:22 CST 2015
     */
    private Integer priority;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.branch2pic.id
     *
     * @return the value of public.branch2pic.id
     *
     * @mbggenerated Sun Jan 25 12:43:21 CST 2015
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.branch2pic.id
     *
     * @param id the value for public.branch2pic.id
     *
     * @mbggenerated Sun Jan 25 12:43:21 CST 2015
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.branch2pic.branch_id
     *
     * @return the value of public.branch2pic.branch_id
     *
     * @mbggenerated Sun Jan 25 12:43:22 CST 2015
     */
    public Long getBranchId() {
        return branchId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.branch2pic.branch_id
     *
     * @param branchId the value for public.branch2pic.branch_id
     *
     * @mbggenerated Sun Jan 25 12:43:22 CST 2015
     */
    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.branch2pic.pic_path
     *
     * @return the value of public.branch2pic.pic_path
     *
     * @mbggenerated Sun Jan 25 12:43:22 CST 2015
     */
    public String getPicPath() {
        return picPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.branch2pic.pic_path
     *
     * @param picPath the value for public.branch2pic.pic_path
     *
     * @mbggenerated Sun Jan 25 12:43:22 CST 2015
     */
    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.branch2pic.type
     *
     * @return the value of public.branch2pic.type
     *
     * @mbggenerated Sun Jan 25 12:43:22 CST 2015
     */
    public Object getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.branch2pic.type
     *
     * @param type the value for public.branch2pic.type
     *
     * @mbggenerated Sun Jan 25 12:43:22 CST 2015
     */
    public void setType(Object type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.branch2pic.priority
     *
     * @return the value of public.branch2pic.priority
     *
     * @mbggenerated Sun Jan 25 12:43:22 CST 2015
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.branch2pic.priority
     *
     * @param priority the value for public.branch2pic.priority
     *
     * @mbggenerated Sun Jan 25 12:43:22 CST 2015
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}