package com.micro.rent.biz.wetalkmgr.upload.service;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.dbaccess.entity.myrent.TUpload;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    String doUpload(MultipartFile file, String fileType);

    void findUploadPaged(Page<TUpload> page);

    void updateStatusAfterUploaded(String status, String filePath);

    /**
     * 上传图片
     *
     * @param file  图片文件
     * @param proId projectId
     * @param houId houseId
     * @return 是否上传成功(true:成功/false:失败)
     */
    boolean doUploadImg(MultipartFile file, String proId, String houId);

}
