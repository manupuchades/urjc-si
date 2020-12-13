package es.urjc.si.dtos.responses;

public class UserResponseDto {

	private Long id;
	private String email;
	private String nick;

	public UserResponseDto() {
		super();
	}

	public UserResponseDto(Long id, String email, String nick) {
		super();
		this.id = id;
		this.email = email;
		this.nick = nick;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", nick=" + nick + "]";
	}
}
