package com.example.jwt.jwtauthentication.controller;

import com.example.jwt.jwtauthentication.jwtauthentication.AuthenticationResponse;
import com.example.jwt.jwtauthentication.jwtauthentication.JwtUtil;
import com.example.jwt.jwtauthentication.jwtauthentication.UserAuthenticationRequest;
import com.example.jwt.jwtauthentication.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/")
    public String hello(){
        return "Hello";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserAuthenticationRequest userAuthenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuthenticationRequest.getUsername(),
                    userAuthenticationRequest.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final String token = jwtUtil.generateToken(userAuthenticationRequest.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {

    }
}
