package es.urjc.si.dtos.responses.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {

	private Long id;
	private String email;
	private String nick;

}
