package com.tlemceni.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tlemceni.security.model.Role;
import com.tlemceni.security.model.User;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
