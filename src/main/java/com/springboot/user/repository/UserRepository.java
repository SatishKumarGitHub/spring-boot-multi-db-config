package com.springboot.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
