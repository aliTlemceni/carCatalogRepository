package com.tlemceni.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tlemceni.entities.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    
    List<Car> findAllByUserId(Long id);	
	Optional<Car> findByMarque(String marque);
	Optional<Car> findByUserUsername(String username);

}
