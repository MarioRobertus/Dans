package com.store.storeApp.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.storeApp.dto.login.LoginReqDto;
import com.store.storeApp.dto.login.LoginResDto;
import com.store.storeApp.model.User;
import com.store.storeApp.service.JwtService;
import com.store.storeApp.service.UserService;

@RestController
@RequestMapping("users")
public class LoginController 
{
	private final UserService userService;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
	public LoginController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService)
	{
		this.userService = userService;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}
	
	@PostMapping("login")
    public ResponseEntity<?> login(@RequestBody final LoginReqDto user) {
        final Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPass());

        authenticationManager.authenticate(auth);
        final Optional<User> userOptional = userService.getByMail(user.getEmail());

        final Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR_OF_DAY, 1);

        final Map<String, Object> claims = new HashMap<>();
        claims.put("exp", cal.getTime());
        claims.put("id", userOptional.get().getId());

        final LoginResDto loginRes = new LoginResDto();
        loginRes.setToken(jwtService.generateJwt(claims));
        
        return new ResponseEntity<>(loginRes, HttpStatus.OK);
    }
}
