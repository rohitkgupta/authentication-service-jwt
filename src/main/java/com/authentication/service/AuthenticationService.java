package com.authentication.service;

import com.authentication.bean.AuthenticationRequest;
import com.authentication.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    @Autowired
    private BlacklistService blacklistService;

    public String createAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception {
        return authenticateAndGenerateToken(authenticationRequest);
    }

    public String refreshToken(AuthenticationRequest authenticationRequest) throws Exception {
        final String token = authenticateAndGenerateToken(authenticationRequest);
        blacklistService.addBlacklistToken(authenticationRequest.getRefreshToken());
        return token;
    }

    private String authenticateAndGenerateToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        return jwtTokenUtil.generateToken(userDetails);
    }

    private void authenticate(String username, String password) throws Exception {
        if (username != null && password != null) {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            } catch (DisabledException e) {
                throw new Exception("USER_DISABLED", e);
            } catch (BadCredentialsException e) {
                throw new Exception("INVALID_CREDENTIALS", e);
            }
        } else {
            throw new Exception("Invalid input.");
        }
    }
}
