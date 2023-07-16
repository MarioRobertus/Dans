package com.store.storeApp.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.store.storeApp.model.User;


public interface UserService extends UserDetailsService
{
	Optional<User> login(String email , String password);
	Optional<User> getByMail(String username);
}
