package com.uni.auth.service;

import com.uni.auth.DTOs.AuthenticationDTO;
import com.uni.auth.DTOs.RegistrationDTO;
import com.uni.auth.DTOs.ResponseRegisterDTO;
import com.uni.auth.domain.Role;
import com.uni.auth.domain.Users;
import com.uni.auth.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

import static java.lang.Long.parseLong;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    public ResponseRegisterDTO register(RegistrationDTO request) {
        var registerUser = usersRepository.findByEmail(request.getEmail());
        Users user = null;
        Users userAdded = null;
        if (registerUser.isEmpty()) {
            user = Users.builder()
                    .firstName(request.getFirstname())
                    .lastName(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();
            userAdded = usersRepository.save(user);
        }
        var jwtToken = jwtService.generateToken(new HashMap<>(), user);
        return ResponseRegisterDTO.builder()
                .token(jwtToken)
                .idUser(userAdded.getIdUser())
                .build();
    }

    public ResponseRegisterDTO authenticate(AuthenticationDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = usersRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(new HashMap<>(), user);
        return ResponseRegisterDTO.builder()
                .token(jwtToken)
                .build();
    }

    public Long getUserId(String token) {
        Optional<Users> user = usersRepository.findByEmail(jwtService.extractUsername(token));
        return user.get().getIdUser();
    }
}
