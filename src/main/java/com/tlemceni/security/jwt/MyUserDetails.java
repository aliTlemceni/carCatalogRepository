package com.tlemceni.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.user.UserRegistryMessageHandler;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tlemceni.security.model.User;
import com.tlemceni.security.repository.UserRepository;


@Service
public class MyUserDetails implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		final User user = userRepository.findByUsername(username);
//
//		ConnectedUser connectedUser = new ConnectedUser(user.getUsername(), user.getPassword(), false, false, false, false,
//				user.getRole().getAuthority(), user.getIpAdresse().getIp());
//
//		return connectedUser;
//	}

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
    final User user = userRepository.findByUsername(username);
    
    if (user == null) {
      throw new UsernameNotFoundException("User '" + username + "' not found");
    }
    
//    ConnectedUser connectedUser = new ConnectedUser(user);
//    return connectedUser;
    return org.springframework.security.core.userdetails.User
        .withUsername(username)//
        .password(user.getPassword())//
        .authorities(user.getAuthorities())//
        .accountExpired(false)//
        .accountLocked(false)//
        .credentialsExpired(false)//
        .disabled(false)//
        .build();
  }
    
	

}
