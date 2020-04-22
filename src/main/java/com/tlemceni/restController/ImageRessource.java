package com.tlemceni.restController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tlemceni.entities.dto.ImageDto;
import com.tlemceni.entities.mapping.ImageMapper;
import com.tlemceni.repository.ImageRepository;
import com.tlemceni.service.interf.ImageService;

@RestController
@RequestMapping(value = "/images/")
public class ImageRessource {

	private final ImageService imageService;
	
	private final ImageRepository imageRepository;
	
	private final ImageMapper imageMapper;

	public ImageRessource(ImageService imageService, ImageRepository imageRepository, ImageMapper imageMapper) {
		super();
		this.imageService = imageService;
		this.imageRepository = imageRepository;
		this.imageMapper = imageMapper;
	}

	@PostMapping(value = "saveImages")
	public ResponseEntity<?> saveImages(@RequestBody List<ImageDto> imageDtos) {

		return ResponseEntity.status(HttpStatus.CREATED).body(this.imageService.saveAllImage(imageDtos));
	}
	
	@GetMapping(value = "imagesByCar/{idCar}")
	public ResponseEntity<List<ImageDto>> getImagesByCarId(@PathVariable("idCar") Long idCar){
		
		return new ResponseEntity<List<ImageDto>>(this.imageService.getAllByCarId(idCar), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<ImageDto>> getImages(){
		
		return new ResponseEntity<List<ImageDto>>(this.imageService.getAllImages(), HttpStatus.OK);
	}
	
	@GetMapping("randomImages")
	public ResponseEntity<List<ImageDto>> get25RandomUniqueImages(){
		
		return new ResponseEntity<List<ImageDto>>(this.imageService.find25RandomImages(), HttpStatus.OK);
	}
	
	@GetMapping("randomImageByID/{idCar}")
	public ResponseEntity<ImageDto> getRandomImageById(@PathVariable("idCar") Long idCar){
		
		return new ResponseEntity<ImageDto>(this.imageService.findRandomImageById(idCar), HttpStatus.OK);
	}
	
	@GetMapping("allImagesByCarId/{idCar}")
	public ResponseEntity<List<ImageDto>> getAllImagesByCarId(@PathVariable("idCar") Long idCar){
		
		return new ResponseEntity<List<ImageDto>>(this.imageService.findAllByCarId(idCar), HttpStatus.OK);
	}
}
