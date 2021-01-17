package es.urjc.si.security;

public class Constants {

	// Spring Security
	public static final String LOGIN_URL = "/login";
	public static final String HEADER_AUTHORIZATION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";

	// JWT
	public static final String ISSUER_INFO = "issuer";
	public static final String SECRET_KEY = "1234";
	public static final long TOKEN_EXPIRATION_TIME = 3600000; // 1h
}
