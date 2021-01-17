package es.urjc.si.controllers.impl;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.si.controllers.UserRestController;
import es.urjc.si.dtos.requests.user.CreateUserRequestDto;
import es.urjc.si.dtos.requests.user.UserLoginRequestDto;
import es.urjc.si.dtos.responses.review.UserReviewResponseDto;
import es.urjc.si.dtos.responses.user.UserResponseDto;
import es.urjc.si.mappers.ReviewMapper;
import es.urjc.si.mappers.UserMapper;
import es.urjc.si.models.User;
import es.urjc.si.security.JwtAuthorizationTokenHelper;
import es.urjc.si.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "User", description = "the User API")
public class UserRestControllerImpl implements UserRestController {

	private UserService userService;

	private UserMapper userMapper;

	private ReviewMapper reviewMapper;

	public UserRestControllerImpl(UserService userService, UserMapper userMapper, ReviewMapper reviewMapper) {
		this.userService = userService;
		this.userMapper = userMapper;
		this.reviewMapper = reviewMapper;
	}

	@Override
	public Collection<UserResponseDto> getUsers() {
		return userMapper.mapToUserResponseDto(userService.findAll());
	}

	@Override
	public ResponseEntity<UserResponseDto> createUser(@Valid CreateUserRequestDto user) {
		User savedUser = userService.save(userMapper.mapToUser(user));
		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location)
				.header("Authorization", "Bearer  " + new JwtAuthorizationTokenHelper().getToken(savedUser.getNick()))
				.body(userMapper.mapToUserResponseDto(savedUser));
	}

	@Override
	public ResponseEntity<String> login(@Valid UserLoginRequestDto user) {
		// Implemented by Spring Security
		return null;
	}

	@Override
	public ResponseEntity<UserResponseDto> getUser(long id) {
		User user = userService.findById(id);

		if (user != null) {
			return ResponseEntity.ok(userMapper.mapToUserResponseDto(user));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	public ResponseEntity<UserResponseDto> updateUser(@Valid CreateUserRequestDto user) {
		return ResponseEntity.ok(userMapper.mapToUserResponseDto(userService.update(userMapper.mapToUser(user))));
	}

	@Override
	public ResponseEntity<UserResponseDto> deleteUser(long id) {
		return ResponseEntity.ok(userMapper.mapToUserResponseDto(userService.deleteById(id)));
	}

	@Override
	public Collection<UserReviewResponseDto> getReviews(@PathVariable Long id) {
		return reviewMapper.mapUserReview(userService.getReviews(id));
	}
}
