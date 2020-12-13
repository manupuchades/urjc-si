package es.urjc.si.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import es.urjc.si.dtos.requests.UserRequestDto;
import es.urjc.si.dtos.responses.UserReviewResponseDto;
import es.urjc.si.dtos.responses.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("/users")
public interface UserRestController {
	
    @Operation(summary = "Get all users")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Users were returned.",
    		content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class)))})})
	@GetMapping("/")
	public Collection<UserResponseDto> getUsers();
    
    @Operation(summary = "Get a user by its id")
    @ApiResponses(value = { 
    		@ApiResponse(responseCode = "200", description = "Found the user", content = {@Content(mediaType = "application/json", schema = @Schema(implementation=UserResponseDto.class))}),
    		@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
    		@ApiResponse(responseCode = "404", description = "User not found", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDto> getUser(@Parameter(description = "the user id") @PathVariable long id);
    
    @Operation(summary = "Create new user")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User to be created", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserRequestDto.class)))
    @ApiResponses(value = { 
    		@ApiResponse(responseCode = "200", description = "User created succesfully."),
    		@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)})
	@PostMapping("/")
	public ResponseEntity<UserResponseDto> createUser(@Parameter(description = "the user")@Valid @RequestBody UserRequestDto user);
    
    @Operation(summary = "Update user")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User to be updated", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserRequestDto.class)))
    @ApiResponses(value = { 
    		@ApiResponse(responseCode = "200", description = "User updated succesfully."),
    		@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
    		@ApiResponse(responseCode = "404", description = "User not found", content = @Content) })
	@PatchMapping("/{id}")
	public ResponseEntity<UserResponseDto> updateUser(@Parameter(description = "the user")@Valid @RequestBody UserRequestDto user);
    
    @Operation(summary = "Delete user")
    @ApiResponses(value = { 
    		@ApiResponse(responseCode = "200", description = "User deleted succesfully."),
    		@ApiResponse(responseCode = "404", description = "User not found", content = @Content) })
    @DeleteMapping("/{id}")
	public ResponseEntity<UserResponseDto> deleteUser(@Parameter(description = "the user id") @PathVariable long id);

    @Operation(summary = "Get all reviews from user")
    @ApiResponses(value = { 
    		@ApiResponse(responseCode = "200", description = "Users were returned.", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class)))}),
    		@ApiResponse(responseCode = "404", description = "User not found", content = @Content) })
    @GetMapping("/{id}/reviews")
    public Collection<UserReviewResponseDto> getReviews(@PathVariable Long id);
}
