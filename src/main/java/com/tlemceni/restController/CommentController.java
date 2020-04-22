package com.tlemceni.restController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tlemceni.entities.dto.CommentDto;
import com.tlemceni.service.interf.CommentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/comments/")
public class CommentController {
	
	private final CommentService commentService;

	
	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}


	@PostMapping(value = "saveComment")
	public ResponseEntity<?> saveComment(@RequestBody CommentDto commentDto) throws Exception {

		return ResponseEntity.status(HttpStatus.CREATED).body(this.commentService.createComment(commentDto));
	}
	
	@GetMapping(value = "commentsByCar/{idCar}")
	public ResponseEntity<List<CommentDto>> getCommentsByCarId(@PathVariable("idCar") Long idCar){
		
		return new ResponseEntity<List<CommentDto>>(this.commentService.getByCarId(idCar), HttpStatus.OK);
	}

}
