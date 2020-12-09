package es.urjc.si.transformers;

import java.util.ArrayList;
import java.util.Collection;

import es.urjc.si.dtos.BookTitleOutDto;
import es.urjc.si.models.Book;

public class BookTransformer {

	public static Collection<BookTitleOutDto> getTitlesCollection(Collection<Book> books){
		Collection<BookTitleOutDto> outDto= new ArrayList<>();
		
		for(Book book:books) {
			outDto.add(new BookTitleOutDto(book));
		}
		
		return outDto;
	}
}
