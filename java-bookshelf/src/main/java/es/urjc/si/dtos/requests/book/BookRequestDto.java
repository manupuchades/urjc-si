package es.urjc.si.dtos.requests.book;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequestDto {
	
    @NotBlank(message = "Author is mandatory")
	private String author;
	
    private String description;
	
    private LocalDate edition;
    
    @NotBlank(message = "Publisher is mandatory")
    private String publisher;
    
    @NotBlank(message = "Title is mandatory")
	private String title;
    
}
