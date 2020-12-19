package es.urjc.si.dtos.responses.review;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserReviewResponseDto {

	private Long id;
	private String comment;
	private int rating;
	private Long bookId;

}
