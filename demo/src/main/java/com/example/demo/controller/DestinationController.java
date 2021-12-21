package com.example.demo.controller;

import com.example.demo.destination.DestinationEntity;
import com.example.demo.service.DestinationService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Destination;
import javax.validation.Valid;
import java.util.List;

//Not working
@RestController
@RequestMapping("/destinations")
public class DestinationController {
     private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @PostMapping("/add")
    public DestinationEntity addDestination(@Valid @RequestBody DestinationEntity destination) {
        return destinationService.addDestination(destination);
    }

    @PutMapping("/update")
    public DestinationEntity updateDestination(@Valid @RequestBody DestinationEntity destination) {
        return destinationService.updateDestination(destination);
    }

    @GetMapping
    public List<DestinationEntity> getDestinations() {
        return destinationService.getAllDestinations();
    }

    @GetMapping("/{id}")
    public DestinationEntity getDestinationById(@PathVariable("id") Long id) {
        return destinationService.findDestinationById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDestinationById(@PathVariable("id") Long id) {
        destinationService.deleteDestination(id);
    }
}
