package com.a2z.repository;

import com.a2z.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Chetanand Meher
 */
public interface UserRepository extends JpaRepository<User, Long> {


    User findByEmail(String email);
}
