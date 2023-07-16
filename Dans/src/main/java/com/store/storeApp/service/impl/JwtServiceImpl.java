package com.store.storeApp.service.impl;

import java.security.KeyPair;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.store.storeApp.service.JwtService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService
{
	private final KeyPair key = Keys.keyPairFor(SignatureAlgorithm.RS256);
	
	@Override
	public String generateJwt(Map<String, Object> claim) 
	{
		final String jwt = Jwts.builder().setClaims(claim).signWith(key.getPrivate()).compact();
		return jwt;
	}

	@Override
	public Map<String, Object> parseJwt(String Jwt) 
	{
		return Jwts.parserBuilder().setSigningKey(key.getPublic()).build().parseClaimsJws(Jwt).getBody();
	}
}
