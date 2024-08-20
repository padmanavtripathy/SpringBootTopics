package com.jwt.JWT.Model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticationResponse {

	private String token;
	private String response;
	public AuthenticationResponse(String token, String response) {
		super();
		this.token = token;
		this.response = response;
	}
}
