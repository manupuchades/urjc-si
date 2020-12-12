package es.urjc.si.controllers;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.si.dtos.requests.BookRequestDto;
import es.urjc.si.dtos.responses.BookResponseDto;
import es.urjc.si.dtos.responses.BookTitleResponseDto;
import es.urjc.si.dtos.responses.BookWithReviewsResponseDto;
import es.urjc.si.mappers.BookMapper;
import es.urjc.si.mappers.ReviewMapper;
import es.urjc.si.models.Book;
import es.urjc.si.services.BookService;
import es.urjc.si.services.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/books")
public class BookControllerRest {

	@Autowired
	private BookService bookService;

	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private ReviewMapper reviewMapper;

    @Operation(summary = "Get all books titles.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Books were returned.",
    		content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BookTitleResponseDto.class)))})})
	@GetMapping("/")
	public Collection<BookTitleResponseDto> getBooks() {
		return bookMapper.map(bookService.findAll());
	}
    
    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = { 
    		@ApiResponse(responseCode = "200", description = "Found the book", content = {@Content(mediaType = "application/json", schema = @Schema(implementation=BookWithReviewsResponseDto.class))}),
    		@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
    		@ApiResponse(responseCode = "404", description = "Book not found", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<BookWithReviewsResponseDto> getBook(@Parameter(description = "the book id") @PathVariable long id) {
		Book book = bookService.findById(id);

		if (book != null) {
			return ResponseEntity.ok(new BookWithReviewsResponseDto(bookMapper.map(book),reviewMapper.map(reviewService.findAll(id))));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

    @Operation(summary = "Create new book.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Book to be created", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookRequestDto.class)))
    @ApiResponses(value = { 
    		@ApiResponse(responseCode = "200", description = "Book created succesfully."),
    		@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)})
	@PostMapping("/")
	public ResponseEntity<BookResponseDto> createBook(@Parameter(description = "the book")@Valid @RequestBody BookRequestDto book) {
		Book savedBook = bookService.save(bookMapper.map(book));
		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(savedBook.getId()).toUri();
		return ResponseEntity.created(location).body(bookMapper.map(savedBook));
	}
}
