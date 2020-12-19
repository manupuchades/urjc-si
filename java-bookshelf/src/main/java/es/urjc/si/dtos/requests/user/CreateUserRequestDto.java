package es.urjc.si.dtos.requests.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateUserRequestDto {

    @NotBlank(message = "email is mandatory")
    @Email(message = "Invalid email")
	private String email;
    
    @NotBlank(message = "Nick is mandatory")
	private String nick;

}
