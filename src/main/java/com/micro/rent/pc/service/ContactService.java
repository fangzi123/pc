package com.micro.rent.pc.service;

import java.util.Map;

import com.micro.rent.pc.entity.Contact;

public interface ContactService {
	Contact findByBranchId(Long branchId) throws Exception;
	
	Contact findHouseDetailContact(Map<String, Object> map) throws Exception;
}
