package com.store.storeApp.service.impl;

import java.util.ArrayList;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.store.storeApp.dao.UserDao;
import com.store.storeApp.model.User;
import com.store.storeApp.service.UserService;
@Service
public class UserServiceImpl implements UserService
{
	private final UserDao userDao;
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	public UserServiceImpl(UserDao userDao)
	{
		this.userDao = userDao;
	}

	@Transactional
	@Override
	public Optional<User> login(String email, String password) 
	{
		return userDao.getEmailANDPassword(email, password);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		final Optional<User> user = userDao.getByMail(username);
		if(user.isPresent())
		{
			return new org.springframework.security.core.userdetails.User(username, user.get().getPassword(), new ArrayList<>());
		}
		throw new UsernameNotFoundException("Login Failed");
	}

	@Override
	public Optional<User> getByMail(String username) 
	{
		return userDao.getByMail(username);
	}
}
