package com.micro.rent.pc.dao;

import java.util.Map;

import com.micro.rent.pc.entity.Address;


public interface AddressDao {
	Address findAddressByBranchId(Map<String, Object> map) throws Exception;

 Long findAddressByAddress(String address) throws Exception;

 void add(Address address) throws Exception;
}