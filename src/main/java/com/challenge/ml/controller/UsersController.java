package com.challenge.ml.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.ml.beans.UsersVO;
import com.challenge.ml.bsn.EnrolamientoBsn;
import com.challenge.ml.dao.UsersRepository;
import com.challenge.ml.entity.Users;

@RestController
public class UsersController {
	@Autowired
	UsersRepository usersDAO;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	EnrolamientoBsn enrolamientoBsn;

	@PostMapping("users/register")
	ResponseEntity<String> newUser(@Valid @RequestBody UsersVO newUser) {
		System.out.println("Entra:" + newUser.toString());
		try {
		Users users = mapper.map(newUser, Users.class);
		List<Users> usersList = usersDAO.findAll();
		for (Users user : usersList) {
			if (user.getUser_name().equals(users.getUser_name()))
				return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe");
		}
		
			usersDAO.save(users);
			return ResponseEntity.status(HttpStatus.CREATED).body("Nuevo usuario agregado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Peticion invalida");
		}
	}

	@PostMapping("users/login/token")
	public ResponseEntity<String> loginToken(@RequestBody UsersVO usersVO) {
		try {
			Users users = mapper.map(usersVO, Users.class);
			users = usersDAO.findByUserAndPwd(usersVO.getUser_name(), usersVO.getPassword());
			usersVO = mapper.map(users, UsersVO.class);
			if (usersVO != null) {
				String token = enrolamientoBsn.getJWTToken(usersVO.getUser_name());
				return ResponseEntity.status(HttpStatus.OK).body(token);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario o contrasenia no validos");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Peticion no valida");
	}
	
	

	   

}
