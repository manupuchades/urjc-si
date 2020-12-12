package es.urjc.si.controllers.rest;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.si.dtos.requests.ReviewRequestDto;
import es.urjc.si.dtos.responses.ReviewResponseDto;
import es.urjc.si.mappers.ReviewMapper;
import es.urjc.si.models.Review;
import es.urjc.si.services.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/reviews")
public class ReviewControllerRest {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ReviewMapper reviewMapper;

	@Operation(summary = "Create new review.")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Review to be created", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReviewRequestDto.class)))
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Review created succesfully."),
			@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
			@ApiResponse(responseCode = "404", description = "Book not found", content = @Content) })
	@PostMapping("/")
	public ResponseEntity<ReviewResponseDto> createReview(@RequestBody ReviewRequestDto review) {
		Review savedReview = reviewService.save(reviewMapper.map(review));
		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(savedReview.getId()).toUri();
		return ResponseEntity.created(location).body(reviewMapper.map(savedReview));
	}

	@Operation(summary = "Delete review")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Review deleted successfully."),
			@ApiResponse(responseCode = "404", description = "Review not found.") })
	@DeleteMapping("/{id}")
	public ResponseEntity<ReviewResponseDto> deleteReview(
			@Parameter(description = "the review id") @PathVariable long id) {
		return ResponseEntity.ok(reviewMapper.map(reviewService.deleteById(id)));
	}

}
