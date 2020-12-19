package es.urjc.si.dtos.requests.review;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewRequestDto {

	@NotBlank(message = "Book id is mandatory")
	private Long bookId;

	private String comment;

	@NotBlank(message = "Rating is mandatory")
	@Min(value = 0, message = "Rating must be equals or greater than 0")
	@Max(value = 5, message = "Rating must be equals or less than 5")
	private int rating;

	@NotBlank(message = "User nick name is mandatory")
	@Size(min = 0, max = 30)
	private String userNick;

}
