package com.challenge.ml.bsn;

import javax.transaction.Transactional;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.dozer.Mapper;
import com.challenge.ml.beans.UsersVO;
import com.challenge.ml.dao.UsersDAO;
import com.challenge.ml.entity.Users;

@Service
@Transactional
public class EnrolamientoBsnImpl implements EnrolamientoBsn {
	
	@Autowired
	UsersDAO usersDAO;
	
	@Autowired
	Mapper mapper;
	
	public UsersVO saveUser(UsersVO newUser) {
		Users users=mapper.map(newUser,Users.class);
		users=usersDAO.save(users);
		UsersVO usersVO=com.challenge.ml.mapper.Mapper.toVO(users);
		return usersVO;
	}
	
	public UsersVO getUser(int userID) {
		UsersVO usersVO=new UsersVO();
		Users users=usersDAO.find(userID);
		usersVO=mapper.map(users,UsersVO.class);
		return usersVO;
	}

}
