package com.tlemceni.controller.modelAssemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.tlemceni.entities.dto.CarDto;
import com.tlemceni.restController.CarController;

@Component
public class CarModelAssembler implements RepresentationModelAssembler<CarDto, EntityModel<CarDto>> {
	
	@Override
	  public EntityModel<CarDto> toModel(CarDto car) {

	    return new EntityModel<>(car,
	      linkTo(methodOn(CarController.class).one(car.getId())).withSelfRel(),
	      linkTo(methodOn(CarController.class).all()).withRel("cars"));
	  }

}
