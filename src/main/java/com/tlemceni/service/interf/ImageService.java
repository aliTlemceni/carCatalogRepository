package com.tlemceni.service.interf;

import java.util.List;

import com.tlemceni.entities.dto.ImageDto;

public interface ImageService {

	List<ImageDto> saveAllImage(List<ImageDto> imageDtos);

	List<ImageDto> getAllByCarId(Long cadreId);
	
	List<ImageDto> getAllImages();
		
	List<ImageDto> find25RandomImages();
	
	ImageDto findRandomImageById(Long idCar);
	
	List<ImageDto> findAllByCarId(Long idCar);

}
