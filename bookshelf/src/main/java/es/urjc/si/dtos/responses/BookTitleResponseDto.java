package es.urjc.si.dtos.responses;

import es.urjc.si.models.Book;

public class BookTitleResponseDto {

	private Long id;
	private String title;
	
	public BookTitleResponseDto() {
		super();
	}

	public BookTitleResponseDto(Book book) {
		super();
		this.id = book.getId();
		this.title = book.getTitle();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
