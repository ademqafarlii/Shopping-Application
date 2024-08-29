package org.adem.orderservice.service;

import org.adem.orderservice.dto.OrderRequest;

public interface OrderService {

    public String placeOrder(OrderRequest orderRequest);
}
