package com.micro.rent.pc.service.comm.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micro.rent.pc.dao.comm.EnumDao;
import com.micro.rent.pc.service.comm.EnumService;


@Transactional
@Service
public class EnumServiceImpl implements EnumService {

    @Autowired
    EnumDao enumDao;
    @Override
    public List<String> findEnum(String str) {
        return enumDao.findEnum(str);
    }
}
