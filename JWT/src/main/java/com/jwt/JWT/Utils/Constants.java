package com.jwt.JWT.Utils;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
@Getter
public class Constants {

	 @Value("${application.security.jwt.secret-key}")
	 public static String SECRET_KEY;
	 
	 @Value("${application.security.jwt.access-token-expiration}")
	 public static Long ACCESS_TOKEN_EXPIRY;
	 
	 @Value("${application.security.jwt.refresh-token-expiration}")
	 public static Long REFRESH_TOKEN_EXPIRY;
	 
	 
	 
}
