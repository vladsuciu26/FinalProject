package com.example.demo.order;

import com.example.demo.OrderStatus;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderDto {

    Long id;

    @NotEmpty
    String dateCreated;

    @NotEmpty
    String lastUpdated;

    @NotEmpty
    OrderStatus status;

    String destination;
}
