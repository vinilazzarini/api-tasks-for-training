package com.devini.controledetarefas.service;

import com.devini.controledetarefas.exception.UserException;
import com.devini.controledetarefas.model.Task;
import com.devini.controledetarefas.model.User;
import com.devini.controledetarefas.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task save(Task task) {
        Optional<User> userOptional = userService.findByUsername(task.getUser().getUsername());

        if (userOptional.isEmpty()){
            throw new UserException("User not found");
        }

        return taskRepository.save(task);
    }

    public List<Task> findByUser(User user) {
        return taskRepository.findByUser(user);
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> findByUserAndCompleted(User user, boolean completed) {
        return taskRepository.findByUserAndCompleted(user,completed);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
