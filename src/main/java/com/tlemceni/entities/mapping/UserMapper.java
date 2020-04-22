package com.tlemceni.entities.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.tlemceni.entities.dto.UserDto;
import com.tlemceni.security.model.User;
//
@Mapper(componentModel = "spring", uses = { RoleMapper.class })
public interface UserMapper extends EntityMapper<UserDto, User> {

	@Mappings({ @Mapping(source = "roles", target = "roleDtos") })
	UserDto toDto(User user);

	@Mappings({ @Mapping(source = "roleDtos", target = "roles") })
	User toEntity(UserDto userDto);

	default User fromId(Long id) {
		if (id == null) {
			return null;
		}
		User user = new User();
		user.setId(id);
		return user;
	}
}
