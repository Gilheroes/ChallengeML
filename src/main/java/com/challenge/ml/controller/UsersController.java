package com.challenge.ml.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.ml.beans.UsersVO;
import com.challenge.ml.bsn.EnrolamientoBsn;

@RestController
public class UsersController {

	@Autowired
	EnrolamientoBsn enrolamientoBsn;
	
	@PostMapping("users/register")
	ResponseEntity<String> newUser(@Valid @RequestBody UsersVO newUser) {
		System.out.println("Entra:" + newUser.toString());
		try {
		if(enrolamientoBsn.validateUser(newUser)) {
			enrolamientoBsn.saveNewUser(newUser);
			return ResponseEntity.status(HttpStatus.CREATED).body("Nuevo usuario agregado");		
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya existe");	 			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Peticion invalida");
		}
	}
	
	@PostMapping("users/login/token")
	public ResponseEntity<String> loginToken(@RequestBody UsersVO usersVO, HttpSession httpSession) {
		try {
			 if (enrolamientoBsn.findByUserAndPwd(usersVO,httpSession) != null) {
				 String token = enrolamientoBsn.getJWTToken(usersVO.getUser_name());
				 return ResponseEntity.status(HttpStatus.OK).body(token);
			}
			 return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario o contrasenia no validos");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Peticion no valida");
		}
	}
	
	

	   

}
