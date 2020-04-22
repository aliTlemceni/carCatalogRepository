package com.tlemceni.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tlemceni.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	List<Comment> findAllByCarId(Long id);

}
