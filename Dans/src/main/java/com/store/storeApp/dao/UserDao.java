package com.store.storeApp.dao;

import java.util.Optional;

import com.store.storeApp.model.User;

public interface UserDao
{
	Optional<User> getEmailANDPassword(String email,String password);
	Optional<User> getByMail(String username);
}
