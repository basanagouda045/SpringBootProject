package com.example.jwt.service;

import com.example.jwt.entity.User;
import com.example.jwt.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String email, String password,String role){

        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("encoded password "+encodedPassword);
        User user = new User();

        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setRole(role);

        userRepository.save(user);
    }

    public User loginUser(String email , String password){

        Optional<User> user = userRepository.findByEmail(email);

        if(user.isEmpty()){
            return null;
        }

        if(passwordEncoder.matches(password,user.get().getPassword())){
            return user.get();
        }

        return null;
    }





}
