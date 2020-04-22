package com.tlemceni.entities.dto;

import java.util.List;

import lombok.Data;

@Data
public class CarDto {
	
	
	private Long id;

	private String marque;
	
	private String carScript;
	
	private Long idUser;
	
	private List<CommentDto> commentDtos;

	

}
