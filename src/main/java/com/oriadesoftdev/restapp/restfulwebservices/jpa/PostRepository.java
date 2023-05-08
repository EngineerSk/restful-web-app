package com.oriadesoftdev.restapp.restfulwebservices.jpa;

import com.oriadesoftdev.restapp.restfulwebservices.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
