package com.tlemceni.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tlemceni.entities.Image;

public interface ImageRepository extends CrudRepository<Image, Long> {
	 List<Image> getAllByCarId(Long cadreId);
     Page<Image> findAll(Pageable pageable);
     
     @Query(value="SELECT * FROM image i where i.car_id = ?1 ORDER BY random() LIMIT 1", nativeQuery = true)
     Image findRandomImage(Long idCar);
     
     @Query(value="SELECT * FROM image ORDER BY random() LIMIT 25", nativeQuery = true)
     List<Image> find25RandomImages();
     
     List<Image> findAllByCarId(Long idCar);
}
