package com.store.storeApp.service;

import java.util.Map;

public interface JwtService 
{
	String generateJwt(Map<String, Object> claim);
	
	Map<String, Object> parseJwt(String Jwt);
}
