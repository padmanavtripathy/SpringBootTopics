package com.jwt.JWT.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.JWT.Model.Token;
import com.jwt.JWT.Model.Login;
import com.jwt.JWT.Model.User;
import com.jwt.JWT.Utils.Constants;
import com.jwt.JWT.Model.repository.TokenRepository;
import com.jwt.JWT.Model.repository.UserRepo;
import com.jwt.JWT.Model.response.AuthenticationResponse;

@Service
public class Authentication {
	private final UserRepo userRepo;
	private final PasswordEncoder passwordEncoder;
	
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final TokenRepository tokenRepository;
	
	
	public Authentication(UserRepo userRepo, PasswordEncoder passwordEncoder, JwtService jwtService,
			AuthenticationManager authenticationManager, TokenRepository tokenRepository) {
		super();
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
		this.tokenRepository = tokenRepository;
	}

	public AuthenticationResponse login(Login login) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				login.getUsername(),
				login.getPassword()));
		
		User user=userRepo.findByUserName(login.getUsername()).orElseThrow();
		String token=jwtService.generateToken(user,Constants.ACCESS_TOKEN_EXPIRY);
		return new AuthenticationResponse(token,"sucess");
	}

	public AuthenticationResponse register(User request) {
		if(userRepo.findByUserName(request.getUsername()).isPresent()) {
            return new AuthenticationResponse(null,"User already exist");
        }
		User user=new User();
		user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUserName(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));


        user.setRole(request.getRole());

        user = userRepo.save(user);

        String accessToken = jwtService.generateToken(user,Constants.ACCESS_TOKEN_EXPIRY);
        saveUserToken(accessToken,user);

        return new AuthenticationResponse(accessToken,"User registration was successful");

	}
	private void saveUserToken(String token, User user) {
        Token accessToken = new Token();
        accessToken.setJwttoken(token);
        accessToken.setUser(user);
        tokenRepository.save(accessToken);
    }
}
