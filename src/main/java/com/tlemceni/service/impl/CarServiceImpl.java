package com.tlemceni.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tlemceni.entities.Car;
import com.tlemceni.entities.dto.CarDto;
import com.tlemceni.entities.dto.ImageDto;
import com.tlemceni.entities.mapping.CarMapper;
import com.tlemceni.repository.CarRepository;
import com.tlemceni.service.interf.CarService;
import com.tlemceni.service.interf.ImageService;

@Service
public class CarServiceImpl implements CarService {

	private final Path rootLocation = Paths.get("C:\\Users\\Ali Tlemceni\\WebstormProjects\\ThreeJs\\src\\assets");

	private final CarRepository carRepository;

	private final CarMapper carMapper;

	private final ImageService imageService;

	public CarServiceImpl(CarRepository carRepository, CarMapper carMapper, ImageService imageService) {
		super();
		this.carRepository = carRepository;
		this.carMapper = carMapper;
		this.imageService = imageService;
	}

	@Override
	@Transactional
	public CarDto saveCar(CarDto carDto, List<MultipartFile> files) throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName());

		Car car = this.carRepository.save(this.carMapper.toEntity(carDto));
		CarDto res = this.carMapper.toDto(car);
		List<ImageDto> images = new ArrayList<ImageDto>();
		if(files!= null) {
		for (MultipartFile multipartFile : files) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			try {
				// Check if the file's name contains invalid characters
				if (fileName.contains("..")) {
					throw new IOException("Sorry! Filename contains invalid path sequence " + fileName);
				}
				ImageDto imageDto = new ImageDto();
				imageDto.setImageName(fileName);
				imageDto.setImageType(multipartFile.getContentType());
				imageDto.setData(multipartFile.getBytes());
				imageDto.setIdCar(res.getId());
				images.add(imageDto);
			} catch (IOException ex) {
				throw new IOException("Could not store file " + fileName + ". Please try again!", ex);
			}
		}
		}
		this.imageService.saveAllImage(images);
		return res;

	}

	@Override
//	@Cacheable(value = "ten-second-cache")
	public List<CarDto> listAllCars() {
		return this.carMapper.toDtos((List<Car>) this.carRepository.findAll());
	}

	@Override
	public void deleteCar(long id) {
		carRepository.deleteById(id);

	}

	@Override
	@Transactional
	public void saveFile(Long id, MultipartFile multiPart) {
		Car car = carRepository.findById(id).get();

		try {
			Files.copy(multiPart.getInputStream(), this.rootLocation.resolve(multiPart.getOriginalFilename()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(this.rootLocation.resolve(multiPart.getOriginalFilename()));
		car.setCarScript(this.rootLocation.resolve(multiPart.getOriginalFilename()).toString());

		carRepository.save(car);

	}

	@Override
	public CarDto findById(Long id) {
		return this.carMapper.toDto(carRepository.findById(id).get());
	}

	@Override
//	@CacheEvict(value = "ten-second-cache", key = "'CarCache'+#marque", beforeInvocation = true)
//	@Cacheable(value = "ten-second-cache", key = "'CarCache'+#marque")
	public CarDto findByMarque(String marque) {
		return this.carMapper.toDto(carRepository.findByMarque(marque).get());
	}

	@Override
	public CarDto findByUsername(String username) {
		return this.carMapper.toDto(carRepository.findByUserUsername(username).get());
	}

}
