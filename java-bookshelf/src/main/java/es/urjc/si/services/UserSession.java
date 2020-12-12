package es.urjc.si.services;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import es.urjc.si.models.Book;

@Component
@SessionScope
public class UserSession {

	private String user;
	private Book book;

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}
