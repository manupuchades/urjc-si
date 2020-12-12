package es.urjc.si.controllers.impl;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.si.controllers.ReviewRestController;
import es.urjc.si.dtos.requests.ReviewRequestDto;
import es.urjc.si.dtos.responses.ReviewResponseDto;
import es.urjc.si.mappers.ReviewMapper;
import es.urjc.si.models.Review;
import es.urjc.si.services.ReviewService;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class ReviewRestControllerImpl implements ReviewRestController{

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private ReviewMapper reviewMapper;

	@Override
	public ResponseEntity<ReviewResponseDto> createReview(@RequestBody ReviewRequestDto review) {
		Review savedReview = reviewService.save(reviewMapper.map(review));
		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(savedReview.getId()).toUri();
		return ResponseEntity.created(location).body(reviewMapper.map(savedReview));
	}

	@Override
	public ResponseEntity<ReviewResponseDto> deleteReview(
			@Parameter(description = "the review id") @PathVariable long id) {
		return ResponseEntity.ok(reviewMapper.map(reviewService.deleteById(id)));
	}

}
