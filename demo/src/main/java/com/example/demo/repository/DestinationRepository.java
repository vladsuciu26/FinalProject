package com.example.demo.repository;

import com.example.demo.destination.DestinationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.print.attribute.standard.Destination;
import java.util.Optional;

@Repository
public interface DestinationRepository extends CrudRepository<DestinationEntity, Long> {

//    void deleteDestinationById(Long id);
//
//    Optional<DestinationEntity> findDestinationById(Long id);

}
