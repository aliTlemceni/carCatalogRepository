package com.tlemceni.service.interf;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tlemceni.entities.dto.UserDto;
import com.tlemceni.security.model.User;


public interface UserService {

	UserDto saveUser(UserDto userDto);


    Page<UserDto> findAll(Pageable pageable);


 
    Optional<UserDto> findOne(Long id);

 
    void delete(Long id);

    UserDto findByUsername(String username);

}
