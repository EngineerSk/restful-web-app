package com.oriadesoftdev.restapp.restfulwebservices.jpa;

import com.oriadesoftdev.restapp.restfulwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
