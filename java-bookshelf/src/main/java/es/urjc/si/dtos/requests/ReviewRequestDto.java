package es.urjc.si.dtos.requests;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ReviewRequestDto {

	@NotBlank(message = "Book id is mandatory")
	private Long bookId;

	private String comment;

	@NotBlank(message = "Rating is mandatory")
	@Min(value = 0, message = "Score must be equals or greater than 0")
	@Max(value = 5, message = "Score must be equals or less than 5")
	private int rating;

	@NotBlank(message = "User nick name is mandatory")
	@Size(min = 0, max = 30)
	private String userNick;

	public ReviewRequestDto(Long bookId, String comment, int rating, String userNick) {
		super();
		this.bookId = bookId;
		this.comment = comment;
		this.rating = rating;
		this.userNick = userNick;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
}
