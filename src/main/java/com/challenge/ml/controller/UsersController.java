package com.challenge.ml.controller;

import com.challenge.ml.beans.UsersVO;
import com.challenge.ml.bsn.EnrolamientoBsn;
import com.challenge.ml.dao.UsersRepository;
import com.challenge.ml.entity.Users;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
		System.out.println("Entra:"+newUser.toString());
		Users users=mapper.map(newUser, Users.class);
		List<Users>usersList=usersDAO.findAll();
		for(Users user:usersList) {
			if(user.getUser_name().equals(users.getUser_name())) 
				return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe");
		}try {
		usersDAO.save(users);
		return ResponseEntity.status(HttpStatus.CREATED).body("Nuevo usuario agregado");
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno");
		}
	}
	
	
	@PostMapping("users/login/token")
	public ResponseEntity<String> loginToken(@RequestBody UsersVO usersVO) {
		try {
			Users users=mapper.map(usersVO, Users.class);
			users=usersDAO.findByUserAndPwd(usersVO.getUser_name(), usersVO.getPassword());
			usersVO=mapper.map(users, UsersVO.class);
			if(usersVO!=null) {
				String token = enrolamientoBsn.getJWTToken(usersVO.getUser_name());
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(token);
			}
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario o contrasenia no validos");
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario o contrasenia no validos");
	}
	


}
