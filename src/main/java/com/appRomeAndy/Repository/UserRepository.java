package com.appRomeAndy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appRomeAndy.Model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	

}
