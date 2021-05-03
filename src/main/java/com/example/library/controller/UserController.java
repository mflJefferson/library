package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.entity.User;
import com.example.library.service.BookServiceImpl;
import com.example.library.service.SecurityServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@SpringBootApplication
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private SecurityServiceImpl securityService;

    @GetMapping("")
    @JsonView(View.User.class)
    public ResponseEntity<Collection<User>> getBooks() {
        return new ResponseEntity<>(securityService.getUsers(), HttpStatus.OK);
    }
}
