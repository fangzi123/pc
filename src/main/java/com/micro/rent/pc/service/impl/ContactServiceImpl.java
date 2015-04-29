package com.micro.rent.pc.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.rent.pc.dao.ContactDao;
import com.micro.rent.pc.entity.Contact;
import com.micro.rent.pc.service.ContactService;
import com.micro.rent.pc.util.MapBuilder;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDao contactDao;
	@Override
	public Contact findByBranchId(Long branchId) throws Exception {
		return contactDao.findByBranchId(MapBuilder.with("branchId", branchId).build());
	}
	@Override
	public Contact findHouseDetailContact(Map<String, Object> map)
			throws Exception {
		return contactDao.findHouseDetailContact(map);
	}

}
