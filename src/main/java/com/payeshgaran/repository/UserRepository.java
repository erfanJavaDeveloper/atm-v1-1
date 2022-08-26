package com.payeshgaran.repository;

import com.payeshgaran.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
//@Query(value = "select u from User u where u.username=:username")
    User findByUsername(String username);
}
