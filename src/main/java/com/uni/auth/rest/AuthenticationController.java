package com.uni.auth.rest;

import com.uni.auth.DTOs.AuthenticationDTO;
import com.uni.auth.DTOs.RegistrationDTO;
import com.uni.auth.DTOs.ResponseRegisterDTO;
import com.uni.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseRegisterDTO> register(@RequestBody RegistrationDTO credentials) {
        return ResponseEntity.ok(authenticationService.register(credentials));
    }

    @PostMapping("/signin")
    public ResponseEntity<ResponseRegisterDTO> authenticate(@RequestBody AuthenticationDTO credentials) {
        return ResponseEntity.ok(authenticationService.authenticate(credentials));
    }

    @GetMapping("/check-token")
    public Long getUserId(@RequestBody String token) {
        return authenticationService.getUserId(token);
    }
}
