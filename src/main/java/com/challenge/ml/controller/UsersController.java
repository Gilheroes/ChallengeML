package com.challenge.ml.controller;

import com.challenge.ml.beans.UsersVO;
import com.challenge.ml.dao.AddressRepository;
import com.challenge.ml.dao.UsersRepository;
import com.challenge.ml.entity.Book;
import com.challenge.ml.entity.Users;
import com.challenge.ml.status.usersStatus;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
	@Autowired
	UsersRepository usersDAO;
	final private ModelMapper mapper = new ModelMapper();
	
	@PostMapping("users/register")
	public usersStatus newUser(@Valid @RequestBody UsersVO newUser) {
		System.out.println("Entra:"+newUser.toString());
		Users users=mapper.map(newUser, Users.class);
		List<Users>usersList=usersDAO.findAll();
		for(Users user:usersList) {
			if(user.equals(users))
				return usersStatus.USER_ALREADY_EXIST;
		}
		usersDAO.save(users);
		return usersStatus.SUCCESS;
	}

}
