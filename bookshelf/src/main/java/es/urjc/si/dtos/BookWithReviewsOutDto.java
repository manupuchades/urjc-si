package es.urjc.si.dtos;

import java.util.ArrayList;
import java.util.Collection;

import es.urjc.si.models.Book;
import es.urjc.si.models.Review;

public class BookWithReviewsOutDto {
	Book book;
	
	ArrayList<Review> review;
	
	public BookWithReviewsOutDto(Book book, Collection<Review> review) {
		super();
		this.book = book;
		this.review = new ArrayList<>(review);
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public ArrayList<Review> getReview() {
		return review;
	}

	public void setReview(ArrayList<Review> review) {
		this.review = review;
	}
}
