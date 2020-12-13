package es.urjc.si.dtos.responses;

public class BookReviewResponseDto {
	private Long id;
	private String comment;
	private int rating;
	private UserResponseDto user;

	public BookReviewResponseDto() {
		super();
	}

	public BookReviewResponseDto(Long id, String comment, int rating, UserResponseDto user) {
		super();
		this.id = id;
		this.comment = comment;
		this.rating = rating;
		this.user = user;
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

	public UserResponseDto getUser() {
		return user;
	}

	public void setUser(UserResponseDto user) {
		this.user = user;
	}
}
