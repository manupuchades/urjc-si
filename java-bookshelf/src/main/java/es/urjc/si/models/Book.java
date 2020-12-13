package es.urjc.si.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String author;
	private String description;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate edition;
	private String publisher;
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> reviews;
	private String title;
	
	public Book() {
		super();
	}
	
	public Book(String author, String description, LocalDate edition, String publisher, String title) {
		super();
		this.author = author;
		this.description = description;
		this.edition = edition;
		this.publisher = publisher;
		this.title = title;
	}

	public Book(String author, String description, LocalDate edition, String publisher, List<Review> reviews,
			String title) {
		this(author, description, edition, publisher, title);
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

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", description=" + description + ", edition=" + edition
				+ ", publisher=" + publisher + ", title=" + title + "]";
	}
}
