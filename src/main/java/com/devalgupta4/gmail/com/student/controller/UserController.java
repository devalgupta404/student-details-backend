package com.devalgupta4.gmail.com.student.controller;

import com.devalgupta4.gmail.com.student.Service.UserService;
import com.devalgupta4.gmail.com.student.dto.CreateUserRequestDto;
import com.devalgupta4.gmail.com.student.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequestDto request) {
        try {
            User user = userService.createUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);
        return user.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/college/{domain}")
    public ResponseEntity<List<User>> getUsersByCollege(@PathVariable String domain) {
        List<User> users = userService.getUsersByCollegeDomain(domain);
        return ResponseEntity.ok(users);
    }
    @GetMapping("/validate-email/{email}")
    public ResponseEntity<Boolean> validateCollegeEmail(@PathVariable String email) {
        boolean isValid = userService.isValidEmailFormat(email);
        return ResponseEntity.ok(isValid);
    }
}
