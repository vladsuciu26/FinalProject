package com.example.demo.service;

import com.example.demo.destination.DestinationEntity;
import com.example.demo.exception.DestinationNotFoundException;
import com.example.demo.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Destination;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

//Not working
@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public DestinationEntity addDestination(DestinationEntity destination) {
        return destinationRepository.save(destination);
    }

    public DestinationEntity updateDestination(DestinationEntity destination) {
        return destinationRepository.save(destination);
    }

    public DestinationEntity findDestinationById(Long id) {
        return destinationRepository.findById(id)
                .orElseThrow(() -> new DestinationNotFoundException("Destination by id " + id + " was not found!"));
    }

    public List<DestinationEntity> getAllDestinations() {
        return StreamSupport.stream(destinationRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void deleteDestination(Long id) {
        destinationRepository.deleteById(id);
    }

}
