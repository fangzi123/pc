package com.micro.rent.biz.wetalkmgr.upload.service.impl;

import com.micro.rent.biz.Constants;
import com.micro.rent.biz.wetalkmgr.upload.service.UploadService;
import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.common.exceptions.BizException;
import com.micro.rent.common.support.Identities;
import com.micro.rent.common.support.ShiroHelper;
import com.micro.rent.dbaccess.dao.myrent.ThousePicDao;
import com.micro.rent.dbaccess.dao.myrent.TprojectPicDao;
import com.micro.rent.dbaccess.dao.myrent.UploadDao;
import com.micro.rent.dbaccess.entity.myrent.TUpload;
import com.micro.rent.dbaccess.entity.myrent.ThousePic;
import com.micro.rent.dbaccess.entity.myrent.TprojectPic;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@Service
@Transactional
public class UploadServiceImpl implements UploadService, InitializingBean {
    @Autowired
    private TprojectPicDao tprojectPicDao;
    @Autowired
    private ThousePicDao thousePicDao;
    private transient Logger log = LoggerFactory.getLogger(this.getClass());
    @Value("${uploadPath}")
    private String uploadPath;
    @Value("${unZipPath}")
    private String unZipPath;
    @Autowired
    private UploadDao uploadDao;
    @Autowired
    private MessageSourceAccessor msa;

    @Override
    public String doUpload(MultipartFile file, String fileType) {

        // 记录上传信息
        Boolean operFlag = true;
        String serverfilePath = "";
        try {
            // 保存到服务器指定路径下
            serverfilePath = copyFile2ServerPath(file, uploadPath);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            operFlag = false;
        }
        String singleFileName = ((CommonsMultipartFile) file).getFileItem()
                .getName();
        TUpload upload = new TUpload();
        upload.setCrtTime(new Date());
        upload.setFileName(singleFileName);
        upload.setFileType(fileType);
        upload.setOper(ShiroHelper.getUsername());
        upload.setResult(operFlag ? "0" : "1");
        upload.setStatus(operFlag ? "0" : "1");
        upload.setRemark(serverfilePath);
        upload.setUploadId(Identities.create32LenUUID());

        uploadDao.insert(upload);

        return serverfilePath;
    }

    /**
     * 上传图片
     *
     * @param file  图片文件
     * @param proId projectId
     * @param houId houseId
     * @return 是否上传成功(true:成功/false:失败)
     */
    @Override
    public boolean doUploadImg(MultipartFile file, String proId, String houId) {
        // 上传成功
        boolean success = true;
        // 记录上传信息
        String serverfilePath = "";
        try {
            if (StringUtils.isNotBlank(proId)) {
                // 保存到服务器指定路径下
                serverfilePath = copyFile2ServerPathNew(file, proId);
                if (StringUtils.isNotBlank(serverfilePath)) {
                    TprojectPic tpp = new TprojectPic();
                    tpp.settProjectPicId(Identities.create32LenUUID());
                    tpp.setProjectId(proId);
                    tpp.setPicture(StringUtils.substringAfter(serverfilePath, unZipPath + File.separator));
                    tprojectPicDao.batchSave(tpp);
                }

            } else if (StringUtils.isNotBlank(houId)) {
                // 保存到服务器指定路径下
                serverfilePath = copyFile2ServerPathNew(file, houId);
                if (StringUtils.isNotBlank(serverfilePath)) {
                    ThousePic hp = new ThousePic();
                    hp.settHousePicId(Identities.create32LenUUID());
                    hp.setHouseId(houId);
                    hp.setPicture(StringUtils.substringAfter(serverfilePath, unZipPath + File.separator));
                    thousePicDao.batchSave(hp);
                }
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            success = false;
        }

        return success;
    }

    /**
     * copy上传文件至指定路径下
     *
     * @param file
     */
    private String copyFile2ServerPath(MultipartFile file, String uploadPath) {
        String singleFileName = ((CommonsMultipartFile) file).getFileItem()
                .getName();

        String fileSeparator = System.getProperty("file.separator");
        int pos = singleFileName.lastIndexOf(fileSeparator);
        if (pos >= 0) {
            singleFileName = singleFileName.substring(pos + 1);
        }
        System.out.println("============singleFileName====" + singleFileName);
        //log.error("============singleFileName====" + singleFileName);
        // 处理上传文件
        String filename = DateTime.now().toString("yyyyMMddHHmmss");
        String newname = filename + singleFileName;

        String newFilePath = uploadPath + fileSeparator
                + DateTime.now().toString("yyyyMMdd");

        File directFile = new File(newFilePath);
        // 目录是否存在
        if (!directFile.isDirectory()) {
            // 创建日期（yyyyMMdd）目录
            directFile.mkdir();
        }

        String path = newFilePath + fileSeparator + newname;

        try {
            // copy 文件
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(
                    path));
        } catch (IOException e1) {
            log.error("文件上传失败:" + e1.getMessage());
            throw new BizException("文件上传失败", e1);
        }

        return path;
    }

    /**
     * 上传文件至指定路径下（保存路径：${basepath}/id/filename）
     *
     * @param file
     * @param id   projectId/houseId
     * @return 文件保存路径
     */
    private String copyFile2ServerPathNew(MultipartFile file, String id) {
        String singleFileName =
                ((CommonsMultipartFile) file).getFileItem().getName();
        log.debug("============singleFileName====" + singleFileName);
        int pos = singleFileName.lastIndexOf(File.separator);
        if (pos != -1) {
            singleFileName = singleFileName.substring(pos + 1);
        }

        // 保存路径
        StringBuilder storePath = new StringBuilder(unZipPath);
        storePath.append(File.separator).append(id);

        File directFile = new File(storePath.toString());
        // 目录是否存在
        if (!directFile.exists()) {
            directFile.mkdirs();
        }

        // 保存文件的全路径
        String path = storePath.toString() + File.separator + singleFileName;
        log.info("File path:" + path);
        try {
            File target = new File(path);
            if (target.exists()) {
                target.delete();
                // 清空文件全路径
                path = "";
            }

            // copy 文件
            FileUtils.copyInputStreamToFile(file.getInputStream(), target);
        } catch (IOException e) {
            throw new BizException("文件上传失败", e);
        }

        return path;
    }

    public void updateStatusAfterUploaded(String status, String filePath) {
        uploadDao.updateStatusAfterUploaded(status, filePath);
    }

    public void findUploadPaged(Page<TUpload> page) {

        List<TUpload> results = uploadDao.findUploadPaged(page);
        if (results != null && !results.isEmpty()) {
            for (TUpload upload : results) {
                Locale locale = (Locale) page.getParams().get(
                        Constants.LANGUAGE);
                upload.setFileType(msa.getMessage(
                        "upload_type_" + upload.getFileType(), locale));
                upload.setResult(msa.getMessage(
                        "upload_result" + upload.getResult(), locale));
                upload.setStatus(msa.getMessage(
                        "upload_status" + upload.getStatus(), locale));
            }
        }

        page.setResults(results);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(uploadPath, "请配置上传路径");
    }


}
