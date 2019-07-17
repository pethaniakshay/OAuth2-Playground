package com.codepuran.ors.controller;

import com.codepuran.ors.dto.UserDto;
import com.codepuran.ors.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
    return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<?> getAllUser() {
    return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }
}
