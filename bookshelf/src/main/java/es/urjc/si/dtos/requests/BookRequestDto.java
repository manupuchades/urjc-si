package es.urjc.si.dtos.requests;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

public class BookRequestDto {
	
    @NotBlank(message = "Author is mandatory")
	private String author;
	private String description;
	private LocalDate edition;
    @NotBlank(message = "Publisher is mandatory")
	private String publisher;
    @NotBlank(message = "Title is mandatory")
	private String title;
    
    public BookRequestDto() {
		super();
	}
    
	public BookRequestDto(@NotBlank(message = "Author is mandatory") String author, String description,
			LocalDate edition, @NotBlank(message = "Publisher is mandatory") String publisher,
			@NotBlank(message = "Title is mandatory") String title) {
		super();
		this.author = author;
		this.description = description;
		this.edition = edition;
		this.publisher = publisher;
		this.title = title;
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
}
