package es.urjc.si.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtAuthorizationTokenHelper {

	public String getToken(String username) {

		Date now = new Date();
		Date validity = new Date(now.getTime() + Constants.TOKEN_EXPIRATION_TIME);

		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(now)
				.setExpiration(validity)
				.signWith(SignatureAlgorithm.HS512, Constants.SECRET_KEY)
				.compact();
	}
}
