package me.williamgalvin.customauth.controller;

import me.williamgalvin.customauth.data.models.User;
import me.williamgalvin.customauth.data.models.UserDTO;
import me.williamgalvin.customauth.exceptions.UserCollisionException;
import me.williamgalvin.customauth.exceptions.UserNotFoundException;
import me.williamgalvin.customauth.service.UserService;
import me.williamgalvin.customauth.utils.HashUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        try {
            userService.createUser(userDTO);
        } catch (UserCollisionException e) {
            return new ResponseEntity<>("Username already taken.", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Successfully created a new user.", HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestParam("id") long id) {
        try {
            userService.deleteUserById(id);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Successfully deleted user.", HttpStatus.OK);
    }

}
