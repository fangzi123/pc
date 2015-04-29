package com.micro.rent.biz.wetalkmgr.upload.service;

public interface SupplierImportService {

    /**
     * 房源导入
     *
     * @param serverFilePath
     */
    void importSupplier(String serverFilePath);

    /**
     * 图片导入
     *
     * @param serverFilePath
     * @author wff
     */
    void importSupplierPic(String serverFilePath);

}
