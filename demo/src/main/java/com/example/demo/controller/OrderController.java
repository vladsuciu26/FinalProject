package com.example.demo.controller;

import com.example.demo.*;

import com.example.demo.destination.entity.DestinationEntity;

import com.example.demo.order.OrderDto;

import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/orders")
public class OrderController {




    private final OrderService orderService;
    private final GetDestinationEntityFromCityName getDestinationEntityFromCityName;
    private final GetDeliveryDateInMillis getDeliveryDateInMillis;



    @Autowired
    public OrderController(OrderService orderService,
                           GetDestinationEntityFromCityName getDestinationEntityFromCityName,
                           GetDeliveryDateInMillis getDeliveryDateInMillis) {

        this.orderService = orderService;
        this.getDestinationEntityFromCityName = getDestinationEntityFromCityName;
        this.getDeliveryDateInMillis = getDeliveryDateInMillis;

    }

    @GetMapping("/status")
    public List<OrderDto> getOrders(@RequestParam(required = false) String date,
                                    @RequestParam(required = false) String destination) throws ParseException {

        if (Objects.nonNull(date) && Objects.nonNull(destination)) {

            long deliveryDateInMillis = getDeliveryDateInMillis.getDeliveryDateInMillis(date);
            DestinationEntity destinationEntity = getDestinationEntityFromCityName.
                    getDestinationEntityFromCityName(destination.trim());

            return orderService.getOrdersByDateAndDestination(deliveryDateInMillis, destinationEntity);

        } else if (Objects.nonNull(date)) {

            long deliveryDateInMillis = getDeliveryDateInMillis.getDeliveryDateInMillis(date);
            return orderService.getOrdersByDate(deliveryDateInMillis);

        } else if (Objects.nonNull(destination)) {

            DestinationEntity destinationEntity = getDestinationEntityFromCityName.
                    getDestinationEntityFromCityName(destination.trim());
            return orderService.getOrdersByDateAndDestination(DisplayCurrentDate.getTimeInMillis(), destinationEntity);
        }

        return orderService.getAllOrders();

    }





    @PostMapping("/cancel")
    public List<OrderDto> cancelOrders(Long... ids) throws ParseException {
        return orderService.cancelOrders(ids);
    }
}
