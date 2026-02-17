package com.jpa.concepts.jpa_concepts.controller;



import com.jpa.concepts.jpa_concepts.DTO.UserDto;
import com.jpa.concepts.jpa_concepts.Entity.User;
import com.jpa.concepts.jpa_concepts.Repository.UserRepository;
import com.jpa.concepts.jpa_concepts.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    public UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }
    @GetMapping
    public List<User> getUser(){
        return userService.getAll();
    }
    @GetMapping("/{userId}")
    public User get(@PathVariable("userId") Long userId){
        return userService.get(Math.toIntExact(userId));
    }
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto savedEntity=userService.createUser(userDto);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }
    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('NORMAL')")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId,User user){
        User savedEntity=userService.updateUser(Math.toIntExact(userId),user);
        return new ResponseEntity<>(savedEntity,HttpStatus.OK);
    }
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(Math.toIntExact(userId));
    }
}
