package com.example.demo.controller;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;

import java.util.HashMap;
import java.util.Map;

//Not working
public class OrderInfoContributor implements InfoContributor {

    Long currentDate;
    Long overallProfit;

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Long> stats = new HashMap<>();
        stats.put("current-date: ", currentDate);
        stats.put("overall-profit", overallProfit);

        builder.withDetail("orders", stats);
    }

    //TODO
    public void displayCurrentDate() {

    }

    //TODO
    public void overallProfit() {

    }
}
