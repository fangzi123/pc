package com.micro.rent.dbaccess.dao.myrent;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.dbaccess.entity.myrent.TUpload;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UploadDao {

    List<TUpload> findUploadPaged(Page<TUpload> page);

    void insert(TUpload upload);

    void updateStatusAfterUploaded(@Param("status") String status, @Param("filePath") String filePath);
}
