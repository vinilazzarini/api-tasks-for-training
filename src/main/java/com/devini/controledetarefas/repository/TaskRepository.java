package com.devini.controledetarefas.repository;

import com.devini.controledetarefas.model.Task;
import com.devini.controledetarefas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository  extends JpaRepository<Task,Long> {
    List<Task> findByUser(User username);
    List<Task> findByUserAndCompleted(User user, boolean completed);

}
