package es.urjc.si.mappers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import es.urjc.si.dtos.requests.BookRequestDto;
import es.urjc.si.dtos.responses.BookResponseDto;
import es.urjc.si.dtos.responses.BookTitleResponseDto;
import es.urjc.si.models.Book;

@Component
public class BookMapper {

	public Collection<BookTitleResponseDto> map(Collection<Book> books) {
		Collection<BookTitleResponseDto> responseDto = new ArrayList<>();

		for (Book book : books) {
			responseDto.add(new BookTitleResponseDto(book));
		}

		return responseDto;
	}

	public BookResponseDto map(Book book) {
		return new BookResponseDto(book.getId(), book.getAuthor(), book.getDescription(), book.getEdition(), book.getPublisher(), book.getTitle());
	}

	public Book map(BookRequestDto dto) {
		return new Book(dto.getAuthor(), dto.getDescription(), dto.getEdition(), dto.getPublisher(), dto.getTitle());
	}
}
