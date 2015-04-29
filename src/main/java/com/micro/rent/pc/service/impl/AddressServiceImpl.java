package com.micro.rent.pc.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.rent.pc.dao.AddressDao;
import com.micro.rent.pc.entity.Address;
import com.micro.rent.pc.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressDao addressDao;

	@Override
	public Address findAddressByBranchId(Long branchId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("branchId", branchId);
		return addressDao.findAddressByBranchId(map);
	}

    @Override
    public Long findAddressByAddress(String address) throws Exception {
        return addressDao.findAddressByAddress(address);
    }

    @Override
    public Long add(Address address) throws Exception {
         addressDao.add(address);
         return address.getId();
    }

}
