package es.urjc.si.controllers.impl;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.si.controllers.BookRestController;
import es.urjc.si.dtos.requests.book.BookRequestDto;
import es.urjc.si.dtos.responses.book.BookDetailsResponseDto;
import es.urjc.si.dtos.responses.book.BookResponseDto;
import es.urjc.si.dtos.responses.book.BookTitleResponseDto;
import es.urjc.si.mappers.BookMapper;
import es.urjc.si.models.Book;
import es.urjc.si.services.BookService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Book", description = "the Book API")
public class BookRestControllerImpl implements BookRestController{

	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookMapper bookMapper;
	
    public BookRestControllerImpl(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @Override
	public Collection<BookTitleResponseDto> getBooks() {
		return bookMapper.mapToBookTitleResponseDto(bookService.findAll());
	}
    
    @Override
	public ResponseEntity<BookDetailsResponseDto> getBook(@Parameter(description = "the book id") @PathVariable long id) {
		Book book = bookService.findById(id);

		if (book != null) {
			return ResponseEntity.ok(bookMapper.mapToBookDetailsResponseDto(book));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

    @Override
	public ResponseEntity<BookResponseDto> createBook(@Parameter(description = "the book")@Valid @RequestBody BookRequestDto book) {
		Book savedBook = bookService.save(bookMapper.mapToBook(book));
		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(savedBook.getId()).toUri();
		return ResponseEntity.created(location).body(bookMapper.mapToBookResponseDto(savedBook));
	}
}
