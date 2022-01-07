package com.example.demo.order.entity;

import com.example.demo.OrderStatus;
import com.example.demo.destination.entity.DestinationEntity;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.LastModifiedDate;

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
    private OrderStatus statusOrder = OrderStatus.NEW;

    private Long lastUpdated;


    public OrderEntity(DestinationEntity destination, Long deliveryDate) {
        this.destination = destination;
        this.deliveryDate = deliveryDate;
    }

    public OrderEntity(DestinationEntity destination, Long deliveryDate, Long lastUpdated) {
        this.destination = destination;
        this.deliveryDate = deliveryDate;
        this.lastUpdated = lastUpdated;
    }

    public OrderEntity() {

    }
}
