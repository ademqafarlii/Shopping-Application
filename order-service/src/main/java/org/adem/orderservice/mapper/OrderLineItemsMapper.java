package org.adem.orderservice.mapper;

import org.adem.orderservice.dto.OrderLineItemsDto;
import org.adem.orderservice.exception.OrderNotFoundException;
import org.adem.orderservice.model.OrderLineItems;
import org.springframework.stereotype.Service;

@Service
public class OrderLineItemsMapper {

    public OrderLineItems toOrderLineItems(OrderLineItemsDto orderLineItemsDto) {
        if (orderLineItemsDto==null){
            throw new OrderNotFoundException("Order not found");
        }
        return OrderLineItems.builder()
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .skuCode(orderLineItemsDto.getSkuCode())
                .build();

    }
}
