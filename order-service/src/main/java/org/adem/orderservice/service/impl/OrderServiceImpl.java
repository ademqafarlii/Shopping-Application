package org.adem.orderservice.service.impl;

import org.adem.orderservice.dto.OrderRequest;
import org.adem.orderservice.dto.inventory.InventoryResponse;
import org.adem.orderservice.event.OrderPlacedEvent;
import org.adem.orderservice.exception.ProductIsNotInStockException;
import org.adem.orderservice.mapper.OrderLineItemsMapper;
import org.adem.orderservice.model.OrderLineItems;
import org.adem.orderservice.repository.OrderRepository;
import org.adem.orderservice.service.OrderService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.adem.orderservice.model.Order;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineItemsMapper orderLineItemsMapper;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;

    public OrderServiceImpl(OrderRepository orderRepository, OrderLineItemsMapper orderLineItemsMapper, WebClient.Builder webClientBuilder, KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.orderLineItemsMapper = orderLineItemsMapper;
        this.webClientBuilder = webClientBuilder;
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public String placeOrder(OrderRequest orderRequest) {
        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsListDto()
                .stream()
                .map(orderLineItemsMapper::toOrderLineItems)
                .toList();

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItemsList(orderLineItemsList);

        List<String > skuCodes = order.getOrderLineItemsList()
                .stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                .uri("http://INVENTORY/v1/inventory/is-in-stock", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        boolean allProductsInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);

        if (allProductsInStock) {
            orderRepository.save(order);
            kafkaTemplate.send("notificationTopic",new OrderPlacedEvent(order.getOrderNumber()));
            return "Order placed successfully";
        } else {
            throw new ProductIsNotInStockException("Product is not in stock, please try again later");
        }

    }

}
