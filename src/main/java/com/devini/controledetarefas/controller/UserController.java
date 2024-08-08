package com.devini.controledetarefas.controller;

import com.devini.controledetarefas.model.User;
import com.devini.controledetarefas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public  ResponseEntity<User> getUserByUsername(@PathVariable String username){
        return userService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        User registeredUser = userService.save(user);
        return ResponseEntity.ok(registeredUser);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username){
        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        userService.deleteByUsername(username);
        return ResponseEntity.noContent().build();
    }
}
