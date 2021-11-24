package com.challenge.ml.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.challenge.ml.entity.Users;

/**
 * Interface used for users.
 *
 * @version 0.0.1
 * @since 0.0.1
 */
@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {
	
	@Query(value="SELECT u FROM Users u WHERE u.user_name=:userName and u.password=:pwd")
	Users findByUserAndPwd(@Param("userName") String user,@Param("pwd") String pwd);

}
