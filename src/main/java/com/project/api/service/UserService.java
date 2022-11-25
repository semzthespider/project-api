package com.project.api.service;

import com.project.api.modul.User;
import com.project.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if(userOptional.isPresent()){
            throw new UserRegistrationException("User with email"+user.getEmail()+"already esists");
        }
        return userRepository.save(user);
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }
}
