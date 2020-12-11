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

import es.urjc.si.dtos.ReviewInDto;
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
	
    @Operation(summary = "Create new review.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Review to be created", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReviewInDto.class)))
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Review created succesfully."),
    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
	@ApiResponse(responseCode = "404", description = "Book not found", content = @Content)})
	@PostMapping("/")
	public ResponseEntity<ReviewInDto> createReview(@RequestBody ReviewInDto review) {
		long id = reviewService.save(review);
		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(location).body(review);
	}
	
    @Operation(summary = "Delete review")
    @ApiResponses(value = { 
    		@ApiResponse(responseCode = "200", description = "Review deleted successfully."),
    		@ApiResponse(responseCode = "404", description = "Review not found.")})
	@DeleteMapping("/{id}")
	public ResponseEntity<Review> deleteReview(@Parameter(description = "the review id") @PathVariable long id) {
		Review review = reviewService.findById(id);

		if (review != null) {
			reviewService.deleteById(id);
			return ResponseEntity.ok(review);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
