package com.isystem.ilibraryapi.controller;

import com.isystem.ilibraryapi.exceptions.UserDuplicateException;
import com.isystem.ilibraryapi.model.User;
import com.isystem.ilibraryapi.repository.UserRepository;
import com.isystem.ilibraryapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.register(user);
            return ResponseEntity.ok(createdUser);
        } catch (UserDuplicateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserId(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Object> updateTask(@PathVariable Long id, @RequestBody User userDetails) {
        User existingUser = userService.getUserById(id);
        try {
            updateIfNotNull(existingUser::setName, userDetails.getName());
            updateIfNotNull(existingUser::setCpf, userDetails.getCpf());
            updateIfNotNull(existingUser::setEmail, userDetails.getEmail());
            updateIfNotNull(existingUser::setPhone, userDetails.getPhone());
            updateIfNotNull(existingUser::setAddress, userDetails.getAddress());
            updateIfNotNull(existingUser::setMembershipDate, userDetails.getMembershipDate());
            return ResponseEntity.ok(userRepository.save(existingUser));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    protected <T> void updateIfNotNull(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }

}
