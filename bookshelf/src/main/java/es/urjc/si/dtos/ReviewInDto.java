package es.urjc.si.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ReviewInDto {

    @NotBlank
	private Long book_id;
	
	@NotBlank
    @Size(min = 0, max = 50)
	private String comment;
	
    @NotBlank
	private int rating;
	
	@NotBlank
    @Size(min = 0, max = 30)
	private String user;
	
	public ReviewInDto(Long book_id, String comment, int rating, String user) {
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
