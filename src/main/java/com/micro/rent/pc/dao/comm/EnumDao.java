package com.micro.rent.pc.dao.comm;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface EnumDao {

    public List<String> findEnum(@Param("enumStr") String enumStr);
}
