package com.tlemceni.service.interf;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tlemceni.entities.dto.RoleDto;
import com.tlemceni.security.model.Role;

public interface RoleService {

	RoleDto saveRole(RoleDto roleDto);


    Page<RoleDto> findAll(Pageable pageable);


 
    Optional<RoleDto> findOne(Long id);

 
    void delete(Long id);
}
