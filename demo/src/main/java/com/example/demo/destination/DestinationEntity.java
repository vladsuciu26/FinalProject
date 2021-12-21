package com.example.demo.destination;

import com.example.demo.order.OrderEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "destinations")
@Data
public class DestinationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private Integer distance;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id")
    private List<OrderEntity> orders;

}
