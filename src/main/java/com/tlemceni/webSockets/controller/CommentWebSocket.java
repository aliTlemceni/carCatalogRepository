package com.tlemceni.webSockets.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.tlemceni.entities.dto.CommentDto;
import com.tlemceni.service.interf.CommentService;

@Controller
@CrossOrigin("*")
public class CommentWebSocket {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
    private CommentService service;
	
    @SubscribeMapping("/{idCar}/get")
    public List<CommentDto> findAllByCarId(@DestinationVariable Long idCar) {
        return service.getByCarId(idCar);
    }

    @MessageMapping("/create")
    @SendTo("/topic/created")
    public CommentDto createComment(CommentDto commentDto) throws Exception {
        return service.createComment(commentDto);
    }

    @MessageExceptionHandler
    @SendToUser("/topic/error")
    public String handleException(Exception ex) {
        logger.debug("Author not found", ex);
        return "The given author was not found";
    }
}
