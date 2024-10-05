package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.EmailException;
import com.example.demo.model.User;
import com.example.demo.payload.EmailPasswordRequest;
import com.example.demo.reponses.TokenResponse;
import com.example.demo.reponses.payload.MessagePayload;
import com.example.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/auth")
@Api(tags = "Auth", description = "Endpoint")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(path = "/signIn")
    public ResponseEntity<Object> authenticationUser(
            @RequestBody EmailPasswordRequest content) {
        try {
            content.isValid();
        } catch (EmailException e) {
            return ResponseEntity.status(301).body(e.getMessage());
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    content.getEmail(), content.getPassword()));

            TokenResponse tR = userService.getTokenResponse(content);
            return ResponseEntity.status(200).body(tR);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("");
        }
    }

    @Operation(summary = "create new user", description = "Create new user with unique email and a password")
    @PostMapping(path = "/signUp")
    public ResponseEntity get(@RequestBody EmailPasswordRequest content) {

        if (content.getEmail().isEmpty()) {
            return ResponseEntity.status(300).body("please give an email");
        }
        if (content.getPassword().isEmpty()) {
            return ResponseEntity.status(300).body("please give a password");
        }
        try {
            userService.save(new User(content.getEmail(), content.getPassword()));
            return ResponseEntity.status(200).body(new MessagePayload("oui"));
        } catch (Exception e) {
            return ResponseEntity.status(300).body(new MessagePayload("non"));
        }
    }
}