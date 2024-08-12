package com.devini.controledetarefas.service;

import com.devini.controledetarefas.exception.UserException;
import com.devini.controledetarefas.model.User;
import com.devini.controledetarefas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        if (userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new UserException("Username already exists");
        }
        return userRepository.save(user);
    }

    public User update(User user){
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User deleteByUsername(String username){ return userRepository.deleteByUsername(username);

    }

}
