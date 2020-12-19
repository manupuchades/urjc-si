package es.urjc.si.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import es.urjc.si.dtos.requests.book.BookRequestDto;
import es.urjc.si.dtos.responses.book.BookDetailsResponseDto;
import es.urjc.si.dtos.responses.book.BookTitleResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("/books")
public interface BookRestController {

    @Operation(summary = "Get all books titles.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Books were returned.",
    		content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BookTitleResponseDto.class)))})})
	@GetMapping("/")
	public Collection<BookTitleResponseDto> getBooks();
    
    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = { 
    		@ApiResponse(responseCode = "200", description = "Found the book", content = {@Content(mediaType = "application/json", schema = @Schema(implementation=BookDetailsResponseDto.class))}),
    		@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
    		@ApiResponse(responseCode = "404", description = "Book not found", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<BookDetailsResponseDto> getBook(@Parameter(description = "the book id") @PathVariable long id);
    
    @Operation(summary = "Create new book.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Book to be created", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookRequestDto.class)))
    @ApiResponses(value = { 
    		@ApiResponse(responseCode = "200", description = "Book created succesfully."),
    		@ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)})
	@PostMapping("/")
	public ResponseEntity<BookDetailsResponseDto> createBook(@Parameter(description = "the book")@Valid @RequestBody BookRequestDto book);
}
