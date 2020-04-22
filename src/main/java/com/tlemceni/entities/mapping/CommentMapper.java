package com.tlemceni.entities.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.tlemceni.entities.Comment;
import com.tlemceni.entities.dto.CommentDto;

@Mapper(componentModel = "spring", uses = { UserMapper.class, CarMapper.class })
public interface CommentMapper extends EntityMapper<CommentDto, Comment> {

	@Mappings({ @Mapping(source = "user.id", target = "idUser"),
		        @Mapping(source = "car.id", target = "idCar")})
	CommentDto toDto(Comment comment);

	@Mappings({ @Mapping(source = "idUser", target = "user.id"),
		        @Mapping(source = "idCar", target = "car.id")})
	Comment toEntity(CommentDto commentDto);

	default Comment fromId(Long id) {
		if (id == null) {
			return null;
		}
		Comment comment = new Comment();
		comment.setId(id);
		return comment;
	}

}
