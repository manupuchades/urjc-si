package es.urjc.si.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Invalid Operation. Shopping cart could not be finalized.")
public class InvalidShoppingCartException extends RuntimeException{

	private static final long serialVersionUID = 3524099316055168939L;
	
    public InvalidShoppingCartException(String message) {
        super(message);
    }

}
