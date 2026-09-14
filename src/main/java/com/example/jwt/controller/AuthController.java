package com.example.jwt.controller;

import ch.qos.logback.core.CoreConstants;
import com.example.jwt.entity.User;
import com.example.jwt.service.JwtService;
import com.example.jwt.service.ProductService;
import com.example.jwt.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {


    private final UserService userService;
    private final JwtService jwtService;


  public   AuthController(UserService userService, JwtService jwtService){
        this.userService = userService;
        this.jwtService = jwtService;

    }

    @PostMapping("/register")
    public String register(@RequestParam String email, @RequestParam String password, @RequestParam String role) {
        userService.registerUser(email,password,role);
        return "User registered successfully";
    }

    @GetMapping("/csrf")
    public CsrfToken csrf(CsrfToken token) {
        return token;
    }

    @PostMapping("/login")
     public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password){

        User user = userService.loginUser(email, password);

        if(user != null){
            return ResponseEntity.status(200).body(jwtService.generateToken(email,user.getRole()));
        }
        return ResponseEntity.status(401).body("Invalid email or password");
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello authenticated user";
    }

}
