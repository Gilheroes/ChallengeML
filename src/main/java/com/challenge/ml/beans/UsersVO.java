package com.challenge.ml.beans;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersVO {

	private Integer id_users;
	private String user_name;
	private String password;

}
