package com.authentication.controller;

import com.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.bean.AuthenticationRequest;
import com.authentication.bean.AuthenticationResponse;

@RestController
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            return ResponseEntity.ok(new AuthenticationResponse(authenticationService.createAuthenticationToken((authenticationRequest))));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/token/refresh", method = RequestMethod.POST)
    public ResponseEntity<?> refreshToken(@RequestBody AuthenticationRequest authenticationRequest) {
        if (authenticationRequest == null || authenticationRequest.getRefreshToken() == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok(new AuthenticationResponse(authenticationService.refreshToken(authenticationRequest)));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
