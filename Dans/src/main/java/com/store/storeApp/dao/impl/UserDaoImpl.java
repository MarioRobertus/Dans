package com.store.storeApp.dao.impl;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.store.storeApp.dao.UserDao;
import com.store.storeApp.model.User;

@Profile("native")
@Repository
public class UserDaoImpl implements UserDao
{
	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<User> getEmailANDPassword(String email, String password) 
	{
		User user = null;
		try
		{
			final String sql = "SELECT tu.id, tu.user_email, tu.user_password, tu.full_name FROM t_user tu "
					+ " WHERE tu.user_email = :email AND tu.user_password = :password ";
			
			final Object result = em.createNativeQuery(sql)
					.setParameter("email", email)
					.setParameter("password", password)
					.getSingleResult();
			
			if(result != null)
			{
				user = new User();
				Object[] objArr = (Object[]) result;
				
				user.setId(Long.valueOf(objArr[0].toString()));
			
				user.setEmail(objArr[1].toString());
				user.setPassword(objArr[2].toString());
				user.setFullName(objArr[3].toString());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Optional.ofNullable(user);
	}

	@Override
	public Optional<User> getByMail(String username) 
	{
		User user = null;
		try
		{
			final String sql = "SELECT tu.id, tu.user_email, tu.user_password, tu.full_name FROM t_user tu "
					+ " WHERE tu.user_email = :email ";
			
			final Object result = em.createNativeQuery(sql)
					.setParameter("email", username)
					.getSingleResult();
			
			if(result != null)
			{
				user = new User();
				Object[] objArr = (Object[]) result;
				
				user.setId(Long.valueOf(objArr[0].toString()));
			
				user.setEmail(objArr[1].toString());
				user.setPassword(objArr[2].toString());
				user.setFullName(objArr[3].toString());

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Optional.ofNullable(user);
	}
}