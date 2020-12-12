package es.urjc.si.mappers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import es.urjc.si.dtos.requests.ReviewRequestDto;
import es.urjc.si.dtos.responses.ReviewResponseDto;
import es.urjc.si.models.Review;

@Component
public class ReviewMapper {
	
	public Collection<ReviewResponseDto> map(Collection<Review> reviews) {
		Collection<ReviewResponseDto> responseDto = new ArrayList<>();

		for (Review review : reviews) {
			responseDto.add(map(review));
		}

		return responseDto;
	}
	
	public ReviewResponseDto map(Review review) {
		return new ReviewResponseDto(review.getId(), review.getComment(), review.getRating(), review.getUser());
	}
	
	public Review map(ReviewRequestDto dto) {
		return new Review(dto.getBook_id(), dto.getComment(), dto.getRating(), dto.getUser());
	}
}
