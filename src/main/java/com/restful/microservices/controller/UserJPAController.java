package com.restful.microservices.controller;

import com.restful.microservices.dto.User;
import com.restful.microservices.respo.UserRepo;
import com.restful.microservices.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserJPAController {


    @Autowired
    private UserRepo userRepo;


    @GetMapping("")
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<User> getUser(@PathVariable int id) {
        User user = userRepo.getById(id);
        if (user == null) {
            throw new RuntimeException();
        }
        EntityModel<User> userEntityModel = EntityModel.of(user);
        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUser()).withRel("all-users");
        userEntityModel.add(link.withRel("all-users"));
        return userEntityModel;
    }

    @PostMapping("")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {

        User user1 = new User(user.getId(), user.getName(), new Date());
        User savedUser = userRepo.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        User user = userRepo.getById(id);
        userRepo.delete(user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /*@GetMapping("/internationalize")
    public String getInternationalize(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return userRepo.getMessage("good.morning.message", null, locale);
        //return "HelloWorld";
    }*/

}
