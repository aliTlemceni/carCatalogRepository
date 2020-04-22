package com.tlemceni.restController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tlemceni.controller.modelAssemblers.CarModelAssembler;
import com.tlemceni.entities.dto.CarDto;
import com.tlemceni.entities.dto.ImageDto;
import com.tlemceni.repository.CarRepository;
import com.tlemceni.service.interf.CarService;

@CrossOrigin("*")
@RestController
@RequestMapping("/cars/")
public class CarController {

	@Autowired
	private CarService carService;

	@Autowired
	private CarModelAssembler carModelAssembler;

	@Autowired
	private CarRepository carRepository;

	@PostMapping(value = "saveCar", consumes = "multipart/form-data")
	public ResponseEntity<?> newCar(@ModelAttribute CarDto car, @ModelAttribute("files") List<MultipartFile> files)
			throws URISyntaxException, IOException {
		EntityModel<CarDto> entityModel = carModelAssembler.toModel(carService.saveCar(car, files));

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);

	}
	@CrossOrigin(origins = "*")
	@PutMapping(value = "updateCar")
	public ResponseEntity<String> updateCar(@RequestBody CarDto carDto) throws IOException {
		
		

		carService.saveCar(carDto, null);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body("the file has been uploaded successfully");
	}


//	@CrossOrigin(origins = "*")
//	@PutMapping(value = "updateCar/{id}/", headers = "content-type=multipart/*")
//	public ResponseEntity<String> handleImagePost(@PathVariable("id") String id,
//			@RequestParam("file") MultipartFile file) {
//
//		carService.saveFile(Long.valueOf(id), file);
//
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body("the file has been uploaded successfully");
//	}

	@GetMapping()
	public ResponseEntity<List<CarDto>> all() {

		List<CarDto> page = carService.listAllCars();
		return new ResponseEntity<List<CarDto>>(page, HttpStatus.OK);

	}

	@GetMapping("{id}")
	public CarDto one(@PathVariable("id") Long id) {
		return this.carService.findById(id);
	}

	@GetMapping("findByMarque/{marque}")
	public ResponseEntity<CarDto> oneByMarque(@PathVariable("marque") String marque) {
		return new ResponseEntity<CarDto>(this.carService.findByMarque(marque), HttpStatus.OK);

	}

	@GetMapping("findByUsername/{username}")
	public EntityModel<CarDto> oneByUsername(@PathVariable("username") String username) {
		CarDto car = carService.findByUsername(username);

		return carModelAssembler.toModel(car);
	}

	@GetMapping("countAllCars")
	public Long countCars() {

		return carRepository.count();
	}
}
