package com.example.springbootsecret.controller;

import com.example.springbootsecret.comment.Signature;
import com.example.springbootsecret.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Signature
    @PostMapping("/{id}")
    public String myController(@PathVariable String id, @RequestParam String name, @RequestBody User user) {
        return String.join(",", id, name, user.toString());
    }
}
