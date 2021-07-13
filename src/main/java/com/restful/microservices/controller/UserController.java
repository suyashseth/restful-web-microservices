package com.restful.microservices.controller;

import com.restful.microservices.dto.User;
import com.restful.microservices.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.hateoas.EntityModel;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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
    public EntityModel<User> getUser(@PathVariable int id){
        User user = service.findOne(id);
        if (user == null){
            throw new RuntimeException();
        }
        EntityModel<User> userEntityModel = EntityModel.of(user);
        Link link= WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUser()).withRel("all-users");
        userEntityModel.add(link.withRel("all-users"));
        return userEntityModel;
    }

    @PostMapping("")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
         User user = service.findOne(id);
         service.delete(user);
         return ResponseEntity.status(HttpStatus.OK).build();
    }

}
