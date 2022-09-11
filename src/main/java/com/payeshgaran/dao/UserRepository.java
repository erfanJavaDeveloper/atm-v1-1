package com.payeshgaran.dao;

import com.payeshgaran.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
//@Query(value = "select u from User u where u.username=:username")
    User findByUsername(String username);
}
