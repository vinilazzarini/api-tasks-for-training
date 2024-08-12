package com.devini.controledetarefas.controller;

import com.devini.controledetarefas.model.Task;
import com.devini.controledetarefas.model.User;
import com.devini.controledetarefas.service.TaskService;
import com.devini.controledetarefas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/{username}/tasks")
    public ResponseEntity<List<Task>> getAllTasksByUsername(@PathVariable String username){
        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        User user = userOptional.get();
        List<Task> tasks = taskService.findByUser(user);

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{username}/tasks/completed")
    public ResponseEntity<List<Task>> getCompletedTasksByUsername(@PathVariable String username){
        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        User user = userOptional.get();
        List<Task> tasks = taskService.findByUserAndCompleted(user,true);

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{username}/tasks/not-completed")
    public ResponseEntity<List<Task>> getNotCompletedTasksByUsername(@PathVariable String username){
        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        User user = userOptional.get();
        List<Task> tasks = taskService.findByUserAndCompleted(user,false);

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{username}")
    public  ResponseEntity<User> getUserByUsername(@PathVariable String username){
        Optional<User> user = userService.findByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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

        User userDeleted = userService.deleteByUsername(username);
        return ResponseEntity.noContent().build();
    }
}
