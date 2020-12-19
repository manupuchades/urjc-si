package es.urjc.si.mappers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import es.urjc.si.dtos.requests.user.CreateUserRequestDto;
import es.urjc.si.dtos.responses.user.ReviewUserResponseDto;
import es.urjc.si.dtos.responses.user.UserResponseDto;
import es.urjc.si.models.User;

@Component
public class UserMapper {

	public Collection<UserResponseDto> mapToUserResponseDto(Collection<User> users) {
		Collection<UserResponseDto> responseDto = new ArrayList<>();

		for (User user : users) {
			responseDto.add(mapToUserResponseDto(user));
		}

		return responseDto;
	}
	
	public UserResponseDto mapToUserResponseDto(User user) {
		return UserResponseDto.builder().id(user.getId()).email(user.getEmail()).nick(user.getNick()).build();
	}
	
	public ReviewUserResponseDto mapToReviewUserResponseDto(User user) {
		return ReviewUserResponseDto.builder().email(user.getEmail()).nick(user.getNick()).build();
	}
	
	public User mapToUser(CreateUserRequestDto dto) {
		return User.builder().email(dto.getEmail()).nick(dto.getNick()).build();
	}
}