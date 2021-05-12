package ru.dlyubanevich.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dlyubanevich.user.domain.User;
import ru.dlyubanevich.user.domain.UserDetails;
import ru.dlyubanevich.user.models.UserData;
import ru.dlyubanevich.user.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/v1/user")
    public User addUser(@RequestBody UserData userData) {
        return userService.save(userData);
    }

    @GetMapping("/api/v1/user")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PutMapping("/api/v1/user/details/{id}")
    public void addUserDetails(@PathVariable String id, @RequestBody UserDetails details) {
        userService.addUserDetails(id, details);
    }

    @PutMapping("/api/v1/user/{id}")
    public void updateUser(@PathVariable String id, @RequestBody UserData userData) {
        userService.update(id, userData);
    }

    @DeleteMapping("/api/v1/user/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.delete(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleNotFound(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
