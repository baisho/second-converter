package com.webstardemo.second_converter.controller;

import com.webstardemo.second_converter.model.AppUser;
import com.webstardemo.second_converter.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<AppUser> findUserById(@PathVariable Long userId) {
        AppUser user = userService.findUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody AppUser user) {
        if (user.getId() != null) {
            return ResponseEntity.badRequest().body("Id is automatically generated, do not set it.");
        }

        AppUser savedUser = userService.save(user);
        return ResponseEntity.status(201).body("Saved user: " + savedUser.getId());
    }

}
