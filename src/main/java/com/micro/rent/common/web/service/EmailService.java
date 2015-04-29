package com.micro.rent.common.web.service;

public interface EmailService {
    // boolean sendOrderMessageToCusetomer(String clientTel, String orderDate,
    // Integer houseId);
    boolean sendOrderMessage(String clientTel, String orderTime,
            String houseAddress, String houseTel, String brandName);
}
