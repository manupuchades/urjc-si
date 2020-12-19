package es.urjc.si.mappers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import es.urjc.si.dtos.requests.UserRequestDto;
import es.urjc.si.dtos.responses.UserResponseDto;
import es.urjc.si.models.User;

@Component
public class UserMapper {

	public Collection<UserResponseDto> map(Collection<User> users) {
		Collection<UserResponseDto> responseDto = new ArrayList<>();

		for (User user : users) {
			responseDto.add(map(user));
		}

		return responseDto;
	}
	
	public UserResponseDto map(User user) {
		return new UserResponseDto(user.getId(), user.getEmail(), user.getNick());
	}
	
	public User map(UserRequestDto dto) {
		return User.builder().email(dto.getEmail()).nick(dto.getNick()).build();
	}
}