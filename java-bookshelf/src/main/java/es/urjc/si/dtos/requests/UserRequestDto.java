package es.urjc.si.dtos.requests;

import javax.validation.constraints.NotBlank;

public class UserRequestDto {

    @NotBlank(message = "email is mandatory")
	private String email;
    @NotBlank(message = "Nick is mandatory")
	private String nick;

	public UserRequestDto() {
		super();
	}

	public UserRequestDto(String email, String nick) {
		super();
		this.email = email;
		this.nick = nick;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
}
