package es.urjc.si.dtos.requests;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ReviewRequestDto {

    @NotBlank(message = "Book id is mandatory")
	private Long book_id;
	
	private String comment;
	
    @NotBlank (message = "Rating is mandatory")
    @Min(value = 0, message = "Score must be equals or greater than 0")
    @Max(value = 5, message = "Score must be equals or less than 5")
	private int rating;
	
	@NotBlank (message = "User is mandatory")
    @Size(min = 0, max = 30)
	private String user;
	
	public ReviewRequestDto(Long book_id, String comment, int rating, String user) {
		super();
		this.book_id = book_id;
		this.comment = comment;
		this.rating = rating;
		this.user = user;
	}

	public Long getBook_id() {
		return book_id;
	}
	
	public void setBook_id(Long book_id) {
		this.book_id = book_id;
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
