package es.urjc.si.dtos.responses;

import es.urjc.si.models.User;

public class UserReviewResponseDto {

	private Long id;
	private String comment;
	private int rating;
	private Long bookId;

	public UserReviewResponseDto() {
		super();
	}

	public UserReviewResponseDto(Long id, String comment, int rating, long bookId) {
		super();
		this.id = id;
		this.comment = comment;
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}