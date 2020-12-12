package es.urjc.si.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.si.exceptions.BookNotFoundException;
import es.urjc.si.models.Book;
import es.urjc.si.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	public Collection<Book> findAll() {
		return bookRepository.findAll();
	}

	public Book findById(long id) {
		return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
	}

	public Book save(Book book) {
		return bookRepository.save(book);
	}

	public Book deleteById(long id) {
		return this.bookRepository.deleteById(id).orElseThrow(BookNotFoundException::new);
	}

}