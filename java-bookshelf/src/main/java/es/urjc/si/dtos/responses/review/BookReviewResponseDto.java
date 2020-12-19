package es.urjc.si.dtos.responses.review;

import es.urjc.si.dtos.responses.user.ReviewUserResponseDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookReviewResponseDto {
	
	private Long id;
	private String comment;
	private int rating;
	private ReviewUserResponseDto user;

}
