package com.jpa.concepts.jpa_concepts.controller;

import com.jpa.concepts.jpa_concepts.DTO.LoginRequest;
import com.jpa.concepts.jpa_concepts.DTO.TokenResponse;
import com.jpa.concepts.jpa_concepts.DTO.UserDto;
import com.jpa.concepts.jpa_concepts.Entity.User;
import com.jpa.concepts.jpa_concepts.Exception.ResourceNotFoundException;
import com.jpa.concepts.jpa_concepts.Repository.UserRepository;
import com.jpa.concepts.jpa_concepts.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationConfiguration authenticationConfiguration;;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> generateToken(
            @RequestBody LoginRequest loginRequest
    ){


        try{
            var authentication=new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword());
            Authentication authenticatedObject=authenticationConfiguration.getAuthenticationManager().authenticate(authentication);
            //now we can fetch the data from the database
            User user=userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()->new ResourceNotFoundException("user not found"));
            if(!user.isEnable()){
                throw new DisabledException("User is Disabled. Contact to admin");
            }

            //access token generate here
            String accessToken=jwtService.generateAccessToken(user);
            String refreshToken=jwtService.generateRefreshToken(user);
            var tokenResponse=new TokenResponse();
            tokenResponse.setUser(modelMapper.map(user, UserDto.class));
            tokenResponse.setAccessToken(accessToken);
            tokenResponse.setRefreshToken(refreshToken);
            return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
        }catch(AuthenticationException e){
            throw new BadCredentialsException("Invalid email or password");
        }
    }
}
