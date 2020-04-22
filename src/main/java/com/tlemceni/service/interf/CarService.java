package com.tlemceni.service.interf;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tlemceni.entities.dto.CarDto;

public interface CarService {

	CarDto saveCar(CarDto car, List<MultipartFile> files) throws IOException;
	void saveFile(Long id, MultipartFile file);
	List<CarDto> listAllCars();
	void deleteCar(long id);
	CarDto findById(Long id);
	CarDto findByMarque(String marque);
	CarDto findByUsername(String username);
	//
}
