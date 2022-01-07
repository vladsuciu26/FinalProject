package com.example.demo;

import com.example.demo.destination.entity.DestinationEntity;
import com.example.demo.destination.repository.DestinationRepository;
import com.example.demo.order.entity.OrderEntity;
import com.example.demo.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class AddDataToOrdersH2 {


    private final DestinationRepository destinationRepository;
    private final OrderRepository orderRepository;
    private final GetDestinationEntityFromCityName getDestinationEntityFromCityName;
    private final GetDeliveryDateInMillis getDeliveryDateInMillis;

    private List<OrderEntity> orderEntityList = new ArrayList<>();

    @Autowired
    public AddDataToOrdersH2(DestinationRepository destinationRepository,
                             OrderRepository orderRepository,
                             GetDestinationEntityFromCityName getDestinationEntityFromCityName,
                             GetDeliveryDateInMillis getDeliveryDateInMillis) {

        this.orderRepository = orderRepository;
        this.destinationRepository = destinationRepository;
        this.getDestinationEntityFromCityName = getDestinationEntityFromCityName;
        this.getDeliveryDateInMillis = getDeliveryDateInMillis;
    }


    public void addOrders() {
        try (BufferedReader br = new BufferedReader(
                new FileReader("src/main/resources/orders.csv"))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                try {
                    orderEntityList.add(getOrderFromCsvLine(line));
                    orderRepository.saveAll(orderEntityList);

                } catch (IllegalArgumentException e) {
                    System.err.println(e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected OrderEntity getOrderFromCsvLine(String line) {

        String[] orderAttributes = line.split(",");

        if (orderAttributes.length != 2) {
            throw new IllegalArgumentException("Corrupted data");
        }


        Long dateCreated = getDeliveryDateInMillis.getDeliveryDateInMillis(orderAttributes[1]);

        DestinationEntity destinationEntity = getDestinationEntityFromCityName.getDestinationEntityFromCityName(orderAttributes[0].trim());

        ZonedDateTime startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault());
        Long lastUpdated = startOfToday.toEpochSecond() * 1000;

        /*LocalDate dateTime1 = DisplayCurrentDate.getCurrentDate();
        long seconds = dateTime1.atStartOfDay(ZoneId.systemDefault())
                .toEpochSecond(); // returns seconds
        long lastUpdated = seconds * 1000; // seconds to milliseconds*/


        return new OrderEntity(destinationEntity,
                dateCreated, lastUpdated);

    }


    public List<OrderEntity> getOrderEntityList() {
        return orderEntityList;
    }
}
