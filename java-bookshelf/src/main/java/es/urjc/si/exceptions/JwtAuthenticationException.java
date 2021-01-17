package es.urjc.si.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Access denied")
public class JwtAuthenticationException extends RuntimeException {

	private static final long serialVersionUID = -5240282871753227742L;
}
