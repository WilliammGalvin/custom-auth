package me.williamgalvin.customauth.controller;

import me.williamgalvin.customauth.data.models.UserDTO;
import me.williamgalvin.customauth.exceptions.UserNotFoundException;
import me.williamgalvin.customauth.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO) {
        boolean authenticated;

        try {
            authenticated = authService.authenticate(userDTO);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        }

        if (!authenticated)
            return new ResponseEntity<>("Invalid credentials.", HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>("Successfully logged in.", HttpStatus.OK);
    }

}
