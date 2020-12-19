package es.urjc.si.dtos.responses.book;

import java.time.LocalDate;
import java.util.Collection;

import es.urjc.si.dtos.responses.review.BookReviewResponseDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDetailsResponseDto {

	private Long id;
	private String author;
	private String description;
	private LocalDate edition;
	private String publisher;
	private String title;

	private Collection<BookReviewResponseDto> reviews;

}
