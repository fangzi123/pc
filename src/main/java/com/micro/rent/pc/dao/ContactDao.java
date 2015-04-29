package com.micro.rent.pc.dao;

import java.util.Map;

import com.micro.rent.pc.entity.Contact;


public interface ContactDao {
	Contact findByBranchId(Map<String, Object> map) throws Exception;
	
	Contact findHouseDetailContact(Map<String, Object> map) throws Exception;
}