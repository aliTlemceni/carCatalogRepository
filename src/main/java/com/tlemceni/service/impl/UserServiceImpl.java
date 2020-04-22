package com.tlemceni.service.impl;
//
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlemceni.entities.dto.UserDto;
import com.tlemceni.entities.mapping.UserMapper;
import com.tlemceni.security.model.User;
import com.tlemceni.security.repository.RoleRepository;
import com.tlemceni.security.repository.UserRepository;
import com.tlemceni.service.interf.UserService;

@Service
public class UserServiceImpl implements UserService {
	private final UserMapper userMapper;
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserMapper userMapper, PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository=roleRepository;
    }

    

    @Override
    @Transactional(readOnly = true)
    public Page<UserDto> findAll(Pageable pageable) {
        log.debug("Recuperer tous : UserDTOs");
        return userRepository.findAll(pageable).map(userMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> findOne(Long id) {
        log.debug("Recuperer un(e)  UserDTO : {}", id);
        Optional<User> user=userRepository.findById(id);
        if(user.isPresent()){
            return Optional.of(userMapper.toDto(user.get()));

        }else{
            return Optional.of(new UserDto());
        }
    }

    @Override
    public void delete(Long id) {
        log.debug("Supprimer UserDTO : {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public UserDto findByUsername(String username) {
        return userMapper.toDto(userRepository.findByUsername(username));
    }

	@Override
	public UserDto saveUser(UserDto userDto) {
		log.debug("Enregistrer  UserDTO : {}", userDto);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
	}

}
