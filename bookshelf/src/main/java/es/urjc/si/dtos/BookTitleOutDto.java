package es.urjc.si.dtos;

import es.urjc.si.models.Book;

public class BookTitleOutDto {

	private Long id;

	private String title;

	public BookTitleOutDto(Book book) {
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
