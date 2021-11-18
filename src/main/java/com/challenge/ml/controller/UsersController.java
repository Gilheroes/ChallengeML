package com.challenge.ml.controller;

import com.challenge.ml.beans.UsersVO;
import com.challenge.ml.dao.AddressRepository;
import com.challenge.ml.dao.UsersRepository;
import com.challenge.ml.entity.Book;
import com.challenge.ml.entity.Users;
import com.challenge.ml.status.usersStatus;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
	@Autowired
	UsersRepository usersDAO;
	
	@PostMapping("users/register")
	public usersStatus newUser(@RequestBody Users newUser) {
		System.out.println("Entra:"+newUser.toString());
		
		List<Users>users=usersDAO.findAll();
		System.out.println("Nuevo usuario: "+newUser);
		for(Users user:users) {
			if(user.equals(newUser))
				return usersStatus.USER_ALREADY_EXIST;
		}
		usersDAO.save(newUser);
		return usersStatus.SUCCESS;
	}

}
