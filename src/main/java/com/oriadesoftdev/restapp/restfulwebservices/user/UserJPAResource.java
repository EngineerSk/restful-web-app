package com.oriadesoftdev.restapp.restfulwebservices.user;

import com.oriadesoftdev.restapp.restfulwebservices.jpa.PostRepository;
import com.oriadesoftdev.restapp.restfulwebservices.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
public class UserJPAResource {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            throw new UserNotFoundException(String.format("id is %d", id));
        return EntityModel.of(user).add(
                WebMvcLinkBuilder
                        .linkTo(
                                WebMvcLinkBuilder
                                        .methodOn(this.getClass())
                                        .retrieveAllUsers()
                        )
                        .withRel("all-users")
        );
    }

    @PostMapping(path = "/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(savedUser.getId())
                                .toUri()
                )
                .build();
    }

    @PostMapping(path = "/jpa/users/{id}/posts")
    public ResponseEntity<Post> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        User savedUser = userRepository.findById(id).orElse(null);
        if (savedUser == null)
            throw new UserNotFoundException(String.format("id is %d", id));
        post.setUser(savedUser);
        Post savedPost = postRepository.save(post);
        return ResponseEntity.created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{post_id}")
                                .buildAndExpand(savedPost.getId())
                                .toUri()
                )
                .build();
    }

    @DeleteMapping(path = "/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }

    @GetMapping(path = "/jpa/users/{id}/posts")
    public List<Post> retrieveAllPostsForUser(@PathVariable int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            throw new UserNotFoundException(String.format("id is %d", id));
        return user.getPosts();
    }

}
