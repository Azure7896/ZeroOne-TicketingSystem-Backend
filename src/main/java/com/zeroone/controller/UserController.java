package com.zeroone.controller;

import com.zeroone.datatransferobjects.GET.TicketDto;
import com.zeroone.datatransferobjects.UserDtoGET;
import com.zeroone.datatransferobjects.UserDtoPOST;
import com.zeroone.exceptions.EmailAlreadyExistsException;
import com.zeroone.exceptions.ValidationException;
import com.zeroone.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDtoPOST userDTOPOST) {
        try {
            userService.registerUser(userDTOPOST);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (EmailAlreadyExistsException | ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getSpecificUserData(@RequestParam("email") String email) {
        return new ResponseEntity<>(userService.getUserData(email), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllTickets() {
        try {
            List<UserDtoGET> usersFromDatabase = userService.getAllUsersData();
            return new ResponseEntity<>(usersFromDatabase, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving users: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
