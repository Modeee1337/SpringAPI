package com.ktu.csgo.insight.authentication;

import com.ktu.csgo.insight.error.exceptions.BadRequestException;
import com.ktu.csgo.insight.user.User;
import com.ktu.csgo.insight.user.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationResponse register(UserRegisterDto request) {
        var user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role())
                .build();

        User userExists = userRepository.findByEmail(request.email()).orElse(null);
        if (userExists != null) {
            throw new BadRequestException("User with this email already exists");
        }

        var savedUser = userRepository.save(user);

        //Add jwt token type to header

        var jwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(savedUser.getEmail())
                .claim("ROLE", savedUser.getRole())
                .signWith(jwtService.getSignInKey(), SignatureAlgorithm.HS256)
                .setExpiration(new java.util.Date(System.currentTimeMillis() + jwtService.getJwtExpiration()))
                .compact();

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}
