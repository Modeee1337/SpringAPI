package com.ktu.csgo.insight.authentication;

import com.ktu.csgo.insight.UserRepository;
import com.ktu.csgo.insight.error.exceptions.BadRequestException;
import com.ktu.csgo.insight.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
                .role("ROLE_USER")//TODO: iskelti veliau i konfiga
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

    public AuthenticationResponse authenticate(@Valid @RequestBody UserLoginDto request) {
        var user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new BadRequestException("User with this email does not exist"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadRequestException("Invalid password");
        }

        var jwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(user.getEmail())
                .claim("ROLE", user.getRole())
                .signWith(jwtService.getSignInKey(), SignatureAlgorithm.HS256)
                .setExpiration(new java.util.Date(System.currentTimeMillis() + jwtService.getJwtExpiration()))
                .compact();

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse updateUser(Integer id, UserRoleUpdateDto request) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("User with this id does not exist"));

        user.setRole(request.role());

        var savedUser = userRepository.save(user);

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
