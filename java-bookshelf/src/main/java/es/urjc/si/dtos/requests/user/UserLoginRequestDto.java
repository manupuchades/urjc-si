package es.urjc.si.dtos.requests.user;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserLoginRequestDto {
    @NotBlank(message = "Nick is mandatory")
	private String username;
    
    @NotBlank(message = "Password is mandatory")
	private String password;
}
