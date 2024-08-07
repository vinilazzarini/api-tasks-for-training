package com.devini.controledetarefas.service;

import com.devini.controledetarefas.model.Task;
import com.devini.controledetarefas.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> findByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> findByIdAndCompleted(Long id, boolean completed) {
        return taskRepository.findByUserIdAndCompleted(id,completed);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
