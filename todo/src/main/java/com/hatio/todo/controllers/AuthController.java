package com.hatio.todo.controllers;

import com.hatio.todo.dtos.AuthRequest;
import com.hatio.todo.dtos.AuthResponse;
import com.hatio.todo.dtos.SignupRequest;
import com.hatio.todo.models.User;
import com.hatio.todo.repositories.UserRepository;
import com.hatio.todo.utils.ApiResponse;
import com.hatio.todo.utils.ErrorResponse;
import com.hatio.todo.utils.JwtUtil;
import com.hatio.todo.utils.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody SignupRequest signupRequest) {
        // Check if the username already exists
        if (userRepository.findByUsername(signupRequest.getUsername()).isPresent()) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Username is already taken"), HttpStatus.BAD_REQUEST);
        }

        try {
            User newUser = new User(signupRequest.getUsername(), passwordEncoder.encode(signupRequest.getPassword()));
            userRepository.save(newUser);
            return new ResponseEntity<>(new SuccessResponse<>(true, 201, "User registered successfully"), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Invalid Credentials!"), HttpStatus.BAD_REQUEST);
        }

        final String token = jwtUtil.generateToken(authRequest.getUsername());
        return new ResponseEntity<>(new SuccessResponse<AuthResponse>(true, 200, new AuthResponse(token)), HttpStatus.OK);
    }
}
