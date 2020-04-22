package com.tlemceni.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tlemceni.entities.dto.RoleDto;
import com.tlemceni.entities.mapping.RoleMapper;
import com.tlemceni.security.model.Role;
import com.tlemceni.security.repository.RoleRepository;
import com.tlemceni.service.interf.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	private final RoleMapper roleMapper;
    private final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleMapper roleMapper, RoleRepository roleRepository) {
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RoleDto> findAll(Pageable pageable) {
        log.debug("Recuperer tous : roleDtos");
        return roleRepository.findAll(pageable).map(roleMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<RoleDto> findOne(Long id) {
        log.debug("Recuperer un(e)  UserDTO : {}", id);
        Optional<Role> role=roleRepository.findById(id);
        if(role.isPresent()){
            return Optional.of(roleMapper.toDto(role.get()));

        }else{
            return Optional.of(new RoleDto());
        }
    }

    @Override
    public void delete(Long id) {
        log.debug("Supprimer RoleDto : {}", id);
        roleRepository.deleteById(id);
    }

	@Override
	public RoleDto saveRole(RoleDto roleDto) {
        return roleMapper.toDto(roleRepository.save(roleMapper.toEntity(roleDto)));
	}
}
