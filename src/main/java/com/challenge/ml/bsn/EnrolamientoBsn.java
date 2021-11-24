package com.challenge.ml.bsn;

import javax.servlet.http.HttpSession;
import com.challenge.ml.beans.UsersVO;

public interface EnrolamientoBsn {
	
	UsersVO saveNewUser(UsersVO usersVO);
	String getJWTToken(String userName);
	boolean validateUser(UsersVO userVO);
	UsersVO findByUserAndPwd(UsersVO usersVO, HttpSession httpSession);

}
