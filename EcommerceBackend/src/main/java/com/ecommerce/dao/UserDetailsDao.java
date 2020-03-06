package com.ecommerce.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
 
@Service
public class UserDetailsDao implements UserDetailsService {
 
  @Autowired
  UserRepository userRepository;
 
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 
    User user = userRepository.findByUsername(username).orElseThrow(
        () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
 
    return UserPrinciple.build(user);
  }
  
  public Optional<User> findOneUser(String username) {
	 
	  return userRepository.findByUsername(username);
	  
	    }
}