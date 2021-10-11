package com.example.demo.repository;

import com.example.demo.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "userInfoRepository")
public interface UserInfoRepository extends JpaRepository<User, Integer> {
  User findByEmail(String username);
}
