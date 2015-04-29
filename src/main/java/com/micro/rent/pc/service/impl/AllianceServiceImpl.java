package com.micro.rent.pc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micro.rent.pc.dao.AllianceDao;
import com.micro.rent.pc.entity.Address;
import com.micro.rent.pc.entity.Alliance;
import com.micro.rent.pc.entity.vo.AllianceVo;
import com.micro.rent.pc.service.AddressService;
import com.micro.rent.pc.service.AllianceService;

@Service
@Transactional
public class AllianceServiceImpl implements AllianceService{
    @Autowired
    private AllianceDao allianceDao;
    @Autowired
    private AddressService addressService;

    @Override
    public Alliance add(AllianceVo ae) throws Exception {
        Long addressId=addressService.findAddressByAddress(ae.getAddress());
        if(addressId==null){
            Address address=new Address();
            address.setZoomLevel("小区");
            address.setAddress(ae.getAddress());
            address.setProvinceId(ae.getProvinceId());
            address.setCityId(ae.getCityId());
            address.setDistrictId(ae.getDistrictId());
            addressId=addressService.add(address);
        }
        ae.setAddressId(addressId);
        allianceDao.add(ae);
        return ae;
    }
    
}
