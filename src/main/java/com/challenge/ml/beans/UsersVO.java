package com.challenge.ml.beans;

public class UsersVO {

	private int id_users;
	private String user_name;
	private String password;
	
	
	
	public int getId_users() {
		return id_users;
	}
	public void setId_users(int id_users) {
		this.id_users = id_users;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UsersVO [idUsers=" + id_users + ", user_name=" + user_name + ", password=" + password + "]";
	}
	
	

	

}
