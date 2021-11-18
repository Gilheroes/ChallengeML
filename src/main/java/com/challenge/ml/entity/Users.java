package com.challenge.ml.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Users extends BaseEntity<Integer>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -646452113163799350L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_users", unique = true, nullable = false)
	private int idUsers;
	@Column(name = "user_name", unique = true, nullable = false)
	private String user_name;
	@Column(name = "password", unique = true, nullable = false)
	private String password;
	@Override
	public String toString() {
		return "Users [idUsers=" + idUsers + ", user_name=" + user_name + ", password=" + password + "]";
	}
	
	

}
