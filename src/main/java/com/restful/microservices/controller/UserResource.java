package com.restful.microservices.controller;

import com.restful.microservices.dto.User;
import com.restful.microservices.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private UserDaoService service;


    @GetMapping("")
    public List<User> getAllUser(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id){
        return service.findOne(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return service.save(user);
    }

}
