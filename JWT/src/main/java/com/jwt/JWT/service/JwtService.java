package com.jwt.JWT.service;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;
import com.jwt.JWT.Utils.Constants;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.jwt.JWT.Model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	public String extractUserName(String token) {
		return extractClaim(token,Claims::getSubject);
	}
	public boolean isValid(String token,UserDetails user) {
		String username=extractUserName(token);
		return (username.equals(user.getUsername()))&& !isTokenExpired(token);
	}
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	public <T> T extractClaim(String token, Function<Claims, T> resolver) {

		Claims claims=extractAllClaimns(token);
		return resolver.apply(claims);
	}
	private Claims extractAllClaimns(String token) {
		System.out.println("Insde extractall Claimns"+token);
		JwtParserBuilder jwtParserBuilder =Jwts.parser().unsecured();
		Claims claims=jwtParserBuilder
				.build()
				.parseUnsecuredClaims(token)
				.getPayload();
		
		System.out.println("Claims"+claims);
		return claims;
	}
	public String generateAccessToken(User user) {
		return generateToken(user,Constants.ACCESS_TOKEN_EXPIRY);
	}
	public String generateRefreshToken(User user) {
		return generateToken(user,Constants.REFRESH_TOKEN_EXPIRY);
	}
	public String generateToken(User user,long expirationTime) {
		String token=Jwts
				.builder()
				.subject(user.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+expirationTime))
				.compact();
		return token;
	}
	
//	private SecretKey getSiginKey() {
//		byte[] keybytes=Decoders.BASE64.decode(Constants.SECRET_KEY);
//		return Keys.hmacShaKeyFor(keybytes);
//	}
}
