package com.payboaz.App.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.payboaz.App.models.UserModel;
import com.payboaz.App.repositories.UserRepository;

@Service
public class UserDetailsServiceImplements implements UserDetailsService {

	private @Autowired UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserModel> optional = repository.findByEmail(username);
		
		if (optional.isPresent()) {
			return new UserDetailsImplements(optional.get());
		} else {
			throw new UsernameNotFoundException(username + " Não existe!");
		}
	}

}
