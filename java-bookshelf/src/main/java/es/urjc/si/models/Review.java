package es.urjc.si.models;

public class Review {

	private Long id;
	private Long book_id;
	private String comment;
	private int rating;
	private String user;
	
	public Review() {
		super();
	}

	public Review(long book_id, String comment, int rating, String user) {
		this(comment, rating, user);
		this.book_id = book_id;
	}
	
	public Review(String comment, int rating, String user) {
		super();
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

	@Override
	public String toString() {
		return "Review [id=" + id + ", comment=" + comment + ", rating=" + rating + ", user=" + user + "]";
	}
}
