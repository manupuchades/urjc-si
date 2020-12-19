package es.urjc.si.mappers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import es.urjc.si.dtos.requests.book.BookRequestDto;
import es.urjc.si.dtos.responses.book.BookDetailsResponseDto;
import es.urjc.si.dtos.responses.book.BookTitleResponseDto;
import es.urjc.si.models.Book;

@Component
public class BookMapper {

	ReviewMapper reviewMapper;
	
	public BookMapper(ReviewMapper reviewMapper) {
		this.reviewMapper = reviewMapper;
	}

	public Collection<BookTitleResponseDto> mapToBookTitleResponseDto(Collection<Book> books) {
		Collection<BookTitleResponseDto> responseDto = new ArrayList<>();

		for (Book book : books) {
			responseDto.add(mapToBookTitleResponseDto(book));
		}

		return responseDto;
	}

	public Collection<BookDetailsResponseDto> mapToBookResponseDto(Collection<Book> books) {
		Collection<BookDetailsResponseDto> responseDto = new ArrayList<>();
		
		for (Book book : books) {
			responseDto.add(mapToBookDetailsResponseDto(book));
		}

		return responseDto;
	}
	
	public BookTitleResponseDto mapToBookTitleResponseDto(Book book) {
		return BookTitleResponseDto.builder().id(book.getId()).title(book.getTitle()).build();
	}
	
	public BookDetailsResponseDto mapToBookDetailsResponseDto(Book book) {
		return BookDetailsResponseDto.builder().id(book.getId()).author(book.getAuthor()).description(book.getDescription()).edition(book.getEdition())
				.publisher(book.getPublisher()).reviews(reviewMapper.mapBookReview(book.getReviews())).title(book.getTitle()).build();
	}

	public Book mapToBook(BookRequestDto dto) {
		return Book.builder().author(dto.getAuthor()).description(dto.getDescription()).edition(dto.getEdition()).publisher(dto.getPublisher()).title(dto.getTitle()).build();
	}
}
