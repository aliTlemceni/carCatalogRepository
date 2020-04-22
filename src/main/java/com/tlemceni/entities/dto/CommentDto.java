package com.tlemceni.entities.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentDto {

	private Long id;
	
	private String content;
	
	private LocalDateTime postedAt;
	
	private Long idCar;
	
	private Long idUser;

}
