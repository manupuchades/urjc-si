package es.urjc.si.controllers.impl;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.si.controllers.UserRestController;
import es.urjc.si.dtos.requests.UserRequestDto;
import es.urjc.si.dtos.responses.UserReviewResponseDto;
import es.urjc.si.dtos.responses.UserResponseDto;
import es.urjc.si.mappers.ReviewMapper;
import es.urjc.si.mappers.UserMapper;
import es.urjc.si.models.User;
import es.urjc.si.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "User", description = "the User API")
public class UserRestControllerImpl implements UserRestController{

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ReviewMapper reviewMapper;
	
	@Override
	public Collection<UserResponseDto> getUsers() {
		return userMapper.map(userService.findAll());
	}

	@Override
	public ResponseEntity<UserResponseDto> getUser(long id) {
		User user = userService.findById(id);

		if (user != null) {
			return ResponseEntity.ok(userMapper.map(user));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	public ResponseEntity<UserResponseDto> createUser(@Valid UserRequestDto user) {
		User savedUser = userService.save(userMapper.map(user));
		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).body(userMapper.map(savedUser));
	}

	@Override
	public ResponseEntity<UserResponseDto> updateUser(@Valid UserRequestDto user) {
		return ResponseEntity.ok(userMapper.map(userService.update(userMapper.map(user))));
	}

	@Override
	public ResponseEntity<UserResponseDto> deleteUser(long id) {
		return ResponseEntity.ok(userMapper.map(userService.deleteById(id)));
	}
	
    @Override
    public Collection<UserReviewResponseDto> getReviews(@PathVariable Long id) {
        return reviewMapper.mapUserReview(userService.getReviews(id));
    }

}
