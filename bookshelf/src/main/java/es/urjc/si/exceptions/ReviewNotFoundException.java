package es.urjc.si.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Review not found")
public class ReviewNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -818285047337272399L;
}
