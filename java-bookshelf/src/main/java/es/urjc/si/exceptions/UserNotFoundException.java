package es.urjc.si.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User not found")
public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 5127602229802833157L;
}
