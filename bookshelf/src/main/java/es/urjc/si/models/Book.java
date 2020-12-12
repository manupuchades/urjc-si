package es.urjc.si.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Book {

	private Long id;
	private String author;
	private String description;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate edition;
	private String publisher;
	private String title;
		
	public Book() {
		super();
		this.author = "";
		this.description = "";
		this.edition = LocalDate.now();
		this.publisher = "";
		this.title = "";
	}
	
	public Book(String author, String description, LocalDate edition, String publisher, String title) {
		super();
		this.author = author;
		this.description = description;
		this.edition = edition;
		this.publisher = publisher;
		this.title = title;
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

	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", description=" + description + ", edition=" + edition
				+ ", publisher=" + publisher + ", title=" + title + "]";
	}
}
