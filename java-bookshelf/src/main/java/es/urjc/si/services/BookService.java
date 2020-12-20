package es.urjc.si.services;

import java.util.Collection;

import org.springframework.stereotype.Service;

import es.urjc.si.exceptions.BookNotFoundException;
import es.urjc.si.models.Book;
import es.urjc.si.repositories.BookRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService {

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
		Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
		this.bookRepository.deleteById(id);
		return book;
	}

}
