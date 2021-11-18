package com.challenge.ml.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.ml.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {

}
