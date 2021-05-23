package ru.dlyubanevich.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dlyubanevich.user.domain.User;
import ru.dlyubanevich.user.domain.UserDetails;
import ru.dlyubanevich.user.models.FileModel;
import ru.dlyubanevich.user.models.UserDataModel;
import ru.dlyubanevich.user.service.MessageService;
import ru.dlyubanevich.user.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MessageService messageService;

    @PostMapping("/api/v1/user")
    public String addUser(@RequestBody UserDataModel userData) {
        User user = userService.save(userData);
        sendMessages(user, userData.getPhoto());
        return user.getId();
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
    public void updateUser(@PathVariable String id, @RequestBody UserDataModel userData) {
        User user = userService.update(id, userData);
        sendMessages(user, userData.getPhoto());
    }

    @DeleteMapping("/api/v1/user/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.delete(id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleNotFound(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private void sendMessages(User user, FileModel photo){
        messageService.sendPhotoMessage(user.getId(), photo);
        messageService.sendNotificationMessage(user);
    }
}
