package es.urjc.si.dtos.responses;

import java.util.ArrayList;
import java.util.Collection;

public class BookWithReviewsResponseDto {
	BookResponseDto book;
	
	ArrayList<ReviewResponseDto> reviews;
	
	public BookWithReviewsResponseDto(BookResponseDto book, Collection<ReviewResponseDto> reviews) {
		super();
		this.book = book;
		this.reviews = new ArrayList<>(reviews);
	}

	public BookResponseDto getBook() {
		return book;
	}

	public void setBook(BookResponseDto book) {
		this.book = book;
	}

	public ArrayList<ReviewResponseDto> getReview() {
		return reviews;
	}

	public void setReview(ArrayList<ReviewResponseDto> review) {
		this.reviews = review;
	}
}
