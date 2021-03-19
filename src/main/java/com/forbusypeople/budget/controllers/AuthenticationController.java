package com.forbusypeople.budget.controllers;

import com.forbusypeople.budget.services.AuthenticationService;
import com.forbusypeople.budget.services.UserDetailsServiceImpl;
import com.forbusypeople.budget.services.dtos.AuthenticationJwtToken;
import com.forbusypeople.budget.services.dtos.UsereDetailsDto;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthenticationController(AuthenticationService authenticationService, UserDetailsServiceImpl userDetailsService) {
        this.authenticationService = authenticationService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping
    public AuthenticationJwtToken getAuthenticationToken(@RequestBody UsereDetailsDto usereDetailsDto) {
        return authenticationService.createAuthenticationToken(usereDetailsDto);
    }

    @PostMapping
    public UUID setUserDetails(@RequestBody UsereDetailsDto usereDetailsDto) {
        return userDetailsService.saveUser(usereDetailsDto);
    }

}
