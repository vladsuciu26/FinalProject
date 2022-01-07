package com.example.demo;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class GetDeliveryDateInMillis {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public long getDeliveryDateInMillis(String date) {
        Date deliveryDate = null;
        try {
            deliveryDate = sdf.parse(date.trim());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long deliveryDateInMillis = deliveryDate.getTime();
        return deliveryDateInMillis;
    }
}
