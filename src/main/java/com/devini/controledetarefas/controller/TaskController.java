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
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping("/{username}")
    public ResponseEntity<Task> createTask(@RequestBody Task task, @PathVariable String username) {

        Optional<User> userOptional = userService.findByUsername(username);
        if (userOptional.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        User user = userOptional.get();
        task.setUser(user);
        Task createdTask = taskService.save(task);

        return ResponseEntity.ok(createdTask);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task){
        return taskService.findById(id)
                .map(existingTask -> {
                    task.setId(existingTask.getId());
                    task.setUser(existingTask.getUser());
                    Task updatedTask = taskService.save(task);
                    return ResponseEntity.ok(updatedTask);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/complete/{id}")
    public ResponseEntity<Task> completeTask(@PathVariable Long id){
        return  taskService.findById(id)
                 .map(task -> {
                     task.setCompleted(true);
                     Task updatedTask = taskService.save(task);
                     return ResponseEntity.ok(updatedTask);
                 })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
