package com.tlemceni.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tlemceni.entities.dto.CommentDto;
import com.tlemceni.entities.mapping.CommentMapper;
import com.tlemceni.repository.CommentRepository;
import com.tlemceni.service.interf.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	private final CommentRepository commentRepository;
	
	private final CommentMapper commentMapper;
	
	

	public CommentServiceImpl(CommentMapper commentMapper, CommentRepository commentRepository) {
		super();
		this.commentMapper=commentMapper;
		this.commentRepository=commentRepository;
	}



	@Override
	public CommentDto createComment(CommentDto commentDto) throws Exception {
        return this.commentMapper.toDto(commentRepository.save(this.commentMapper.toEntity(commentDto)));
	}



	@Override
	public List<CommentDto> getByCarId(Long id) {
		return this.commentMapper.toDtos(commentRepository.findAllByCarId(id));
	}
}
