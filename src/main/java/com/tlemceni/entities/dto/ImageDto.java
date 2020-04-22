package com.tlemceni.entities.dto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tlemceni.entities.Car;

import lombok.Data;

@Data
public class ImageDto {

	private Long id;

	private String imageName;

	private String imageType;

	private byte[] data;

	private Long idCar;

}
