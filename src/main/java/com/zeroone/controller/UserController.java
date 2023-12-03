package com.zeroone.controller;

import com.zeroone.datatransferobjects.UserDto;
import com.zeroone.exceptions.EmailAlreadyExistsException;
import com.zeroone.exceptions.ValidationException;
import com.zeroone.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDTO) {
        System.out.println(userDTO.getPassword());
        System.out.println(userDTO.getEmail());
        System.out.println(userDTO.getFirstName());
        System.out.println(userDTO.getLastName());
        try {
            userService.registerUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (EmailAlreadyExistsException | ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getChartsData(@RequestParam("email") String email) {
        return new ResponseEntity<>(userService.getUserData(email), HttpStatus.OK);
    }

}
