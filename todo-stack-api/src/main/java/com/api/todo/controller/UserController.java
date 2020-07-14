package com.api.todo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.api.todo.model.User;

@RestController
public class UserController {
    @GetMapping("/")
    public String getUserStatus() {
        return "Todo Management Response.";
    }
    @GetMapping("/user")
    public User getUserBean() {
    	return new User("Marikannan", "Shanmugam");
    }
    @GetMapping("/user/{firstname}/{lastname}")
    public User getUserBeanWithParam(@PathVariable String firstname, @PathVariable String lastname) {
    	return new User(firstname, lastname);
    }
}
