package es.urjc.si.dtos.responses;

public class ReviewResponseDto {

	private Long id;
	private String comment;
	private int rating;
	private String user;

	public ReviewResponseDto() {
		super();
	}

	public ReviewResponseDto(Long id, String comment, int rating, String user) {
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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
