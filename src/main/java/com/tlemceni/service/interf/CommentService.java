package com.tlemceni.service.interf;

import java.util.List;

import com.tlemceni.entities.dto.CommentDto;


public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto) throws Exception;
	
	List<CommentDto> getByCarId(Long id);

}
