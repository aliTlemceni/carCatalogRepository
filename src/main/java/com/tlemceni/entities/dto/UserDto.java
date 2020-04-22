 package com.tlemceni.entities.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDto {
	
	private Long id;
	
	private String email;
	
	private String username;
	
	private String password;
	
	private List<RoleDto> roleDtos;
	

}
