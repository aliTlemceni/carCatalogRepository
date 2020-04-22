package com.tlemceni.entities.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.tlemceni.entities.dto.RoleDto;
import com.tlemceni.security.model.Role;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface RoleMapper extends EntityMapper<RoleDto, Role> {
	
	RoleDto toDto(Role role);
	
	@Mappings({ @Mapping(target = "users", ignore = true)})
	Role toEntity(RoleDto roleDto);

	default Role fromId(Long id) {
		if (id == null) {
			return null;
		}
		Role role = new Role();
		role.setId(id);
		return role;
	}
}
