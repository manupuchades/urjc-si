package es.urjc.si.dtos.responses.book;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookTitleResponseDto {

	private Long id;
	private String title;
	
}
