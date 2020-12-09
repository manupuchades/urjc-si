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
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/reviews")
public class ReviewControllerRest {

	@Autowired
	private ReviewService reviewService;
	
    @Operation(summary = "Create review.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Review created succesfully.")})
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
	public ResponseEntity<Review> deleteReview(@PathVariable long id) {
		Review review = reviewService.findById(id);

		if (review != null) {
			reviewService.deleteById(id);
			return ResponseEntity.ok(review);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
