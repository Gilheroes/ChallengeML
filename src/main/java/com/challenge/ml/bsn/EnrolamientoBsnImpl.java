package com.challenge.ml.bsn;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.challenge.ml.beans.UsersVO;
import com.challenge.ml.dao.UsersRepository;
import com.challenge.ml.entity.Users;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class EnrolamientoBsnImpl implements EnrolamientoBsn {
@Autowired
UsersRepository usersRepository;
@Autowired
private ModelMapper mapper;
	
	
	@Override
	public String getJWTToken(String userName) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("jwtChallenge")
				.setSubject(userName)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
	
	@Override
	public UsersVO saveNewUser(UsersVO newUser) {
		Users users = mapper.map(newUser, Users.class);
		UsersVO result=mapper.map(usersRepository.save(users),UsersVO.class);
		return result;
	}

	@Override
	public boolean validateUser(UsersVO userVO) {
		try {
			Users userEntity=mapper.map(userVO, Users.class);
			List<Users> usersList = usersRepository.findAll();
			for (Users user : usersList) {
				if (user.getUser_name().equals(userEntity.getUser_name()))
					return false;
			}
					return true;
			} catch (Exception e) {
				return false;
			}
	}

	@Override
	public UsersVO findByUserAndPwd(UsersVO usersVO, HttpSession httpSession) {
    	try {
			Users users = mapper.map(usersVO, Users.class);
			users = usersRepository.findByUserAndPwd(usersVO.getUser_name(), usersVO.getPassword());
			System.out.println(users.getIdUsers());
			usersVO = mapper.map(users, UsersVO.class);
			System.out.println("id: "+users.getIdUsers());
			httpSession.setAttribute("id",users.getIdUsers());
			return usersVO;
    	}catch (Exception e) {
    		return null;
    	}
	}



	

}
