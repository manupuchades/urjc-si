package es.urjc.si.dtos.responses;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import es.urjc.si.models.Book;

public class BookResponseDto {

	private Long id;
	private String author;
	private String description;
	private LocalDate edition;
	private String publisher;
	private String title;

	private Collection<BookReviewResponseDto> reviews;

	public BookResponseDto() {
		super();
	}

	public BookResponseDto(Long id, String author, String description, LocalDate edition, String publisher,
			String title) {
		super();
		this.id = id;
		this.author = author;
		this.description = description;
		this.edition = edition;
		this.publisher = publisher;
		this.title = title;
	}

	public BookResponseDto(Book book, Collection<BookReviewResponseDto> reviews) {
		this(book.getId(), book.getAuthor(), book.getDescription(), book.getEdition(), book.getPublisher(), book.getTitle());
		this.reviews = new ArrayList<>(reviews);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getEdition() {
		return edition;
	}

	public void setEdition(LocalDate edition) {
		this.edition = edition;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Collection<BookReviewResponseDto> getReviews() {
		return reviews;
	}

	public void setReviews(Collection<BookReviewResponseDto> reviews) {
		this.reviews = reviews;
	}
}
