package es.urjc.si.dtos.responses.book;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookResponseDto {

	private Long id;
	private String author;
	private String description;
	private LocalDate edition;
	private String publisher;
	private String title;
}
