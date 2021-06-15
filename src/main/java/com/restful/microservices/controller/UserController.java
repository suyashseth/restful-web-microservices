package com.restful.microservices.controller;

import com.restful.microservices.dto.User;
import com.restful.microservices.exception.MetadataDoesNotExistException;
import com.restful.microservices.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDaoService service;


    @GetMapping("")
    public List<User> getAllUser(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable int id){
        return Optional.of(service.findOne(id)
                .orElseThrow(()-> new MetadataDoesNotExistException(id + "Not present")));
    }

    @PostMapping("")
    public User createUser(@RequestBody User user){
        return service.save(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
         User user = service.findOne(id)
                 .orElseThrow(()-> new MetadataDoesNotExistException(id + "Not present"));
         service.delete(user);
         return ResponseEntity.status(HttpStatus.OK).build();
    }

}
