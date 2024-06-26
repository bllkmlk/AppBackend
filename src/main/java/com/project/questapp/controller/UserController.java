package com.project.questapp.controller;

import com.project.questapp.entities.User;
import com.project.questapp.repos.UserRepository;
import com.project.questapp.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private UserService userService;

    public UserController( UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser){
        return userService.saveOneUser(newUser);
    }
    @GetMapping( "/{userId}")
    public User getOneUser(@PathVariable Long userId){
        //  custom exception
        return userService.getOneUserById(userId);
    }
    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId,@RequestBody User newUser){
        return userService.updateOneUser(userId,newUser);
    }
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteById(userId);
    }
}
