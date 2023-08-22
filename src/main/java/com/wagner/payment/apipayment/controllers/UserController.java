package com.wagner.payment.apipayment.controllers;

import com.wagner.payment.apipayment.dtos.UserDTO;
import com.wagner.payment.apipayment.domain.user.UserEntity;
import com.wagner.payment.apipayment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserDTO user){
        UserEntity newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllusers(){
       List<UserEntity> users = this.userService.getAllUsers();
       return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
