package com.example.demo.service;

import com.example.demo.OrderStatus;
import com.example.demo.destination.entity.DestinationEntity;
import com.example.demo.order.OrderConverter;
import com.example.demo.order.OrderDto;
import com.example.demo.order.entity.OrderEntity;
import com.example.demo.order.repository.OrderRepository;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public List<OrderDto> getAllOrders() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .map(OrderConverter::fromOrderEntity)
                .collect(Collectors.toList());
    }

    public List<OrderDto> getOrdersByDateAndDestination(long date, DestinationEntity destination) {

        return orderRepository.findOrderByDeliveryDateAndDestination(date, destination).stream()
                .map(OrderConverter::fromOrderEntity)
                .collect(Collectors.toList());
    }

    public List<OrderDto> getOrdersByDate(long deliveryDateInMillis) {
        return orderRepository.findOrderByDeliveryDate(deliveryDateInMillis).stream()
                .map(OrderConverter::fromOrderEntity)
                .collect(Collectors.toList());
    }


    public List<OrderDto> findOrderById(Long id) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);

        return orderEntityOptional.map(orderEntity -> Collections.singletonList(
                OrderConverter.fromOrderEntity(orderEntity))).orElse(Collections.emptyList());
    }


    public List<OrderDto> cancelOrders(Long[] ids) throws ParseException {
        List<OrderDto> ordersToBeCanceled = null;
        List<OrderDto> canceledOrders = null;

        OrderEntity orderEntity = null; //DestinationConverter.fromDestinationDto(destinationDto);

        OrderEntity savedOrder = null; //destinationRepository.save(destinationEntity);

        for (Long id : ids) {
            ordersToBeCanceled = findOrderById(id);
        }

        assert ordersToBeCanceled != null;

        for (OrderDto orderDto : ordersToBeCanceled) {
            if (orderDto.getStatus() != OrderStatus.DELIVERED) {
                orderDto.setStatus(OrderStatus.CANCELED);

                orderEntity = OrderConverter.fromOrderEntityDto(orderDto);
                savedOrder = orderRepository.save(orderEntity);

                canceledOrders.add(orderDto);
            }
        }

        return canceledOrders;

    }
}
