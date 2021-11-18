package com.challenge.ml.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersVO {

	private Integer id_users;
	private String user_name;
	private String password;

	@Override
	public String toString() {
		return "UsersVO [idUsers=" + id_users + ", user_name=" + user_name + ", password=" + password + "]";
	}
	
	

	

}
