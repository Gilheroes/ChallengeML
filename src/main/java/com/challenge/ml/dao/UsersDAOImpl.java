package com.challenge.ml.dao;

import org.springframework.stereotype.Repository;

import com.challenge.ml.entity.Users;

@Repository
public class UsersDAOImpl extends HibernateBaseDAO<Integer, Users> implements UsersDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 329857387635464596L;

}
