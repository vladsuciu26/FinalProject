package com.example.demo.order;

import com.example.demo.OrderStatus;
import com.example.demo.destination.DestinationEntity;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "orders")
@Data
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private DestinationEntity destination;

    private Long deliveryDate;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus statusOrder;

    private Long lastUpdated;

}
