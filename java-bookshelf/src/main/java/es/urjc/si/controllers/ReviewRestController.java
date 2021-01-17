package es.urjc.si.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import es.urjc.si.dtos.requests.review.ReviewRequestDto;
import es.urjc.si.dtos.responses.review.UserReviewResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("/reviews")
public interface ReviewRestController {

	@Operation(summary = "Create new review.")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Review to be created", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReviewRequestDto.class)))
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Review created succesfully."),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
			@ApiResponse(responseCode = "403", description = "Access denied"),
			@ApiResponse(responseCode = "404", description = "Book not found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Expired or invalid JWT token")})
	@PostMapping("/")
	public ResponseEntity<UserReviewResponseDto> createReview(@RequestBody ReviewRequestDto review);

	@Operation(summary = "Delete review")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Review deleted successfully."),
			@ApiResponse(responseCode = "403", description = "Access denied"),
			@ApiResponse(responseCode = "404", description = "Review not found."),
			@ApiResponse(responseCode = "500", description = "Expired or invalid JWT token")})
	@DeleteMapping("/{id}")
	public ResponseEntity<UserReviewResponseDto> deleteReview(
			@Parameter(description = "the review id") @PathVariable long id);
}
