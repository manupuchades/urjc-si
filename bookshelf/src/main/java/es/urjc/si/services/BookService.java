package es.urjc.si.services;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import es.urjc.si.models.Book;

@Service
public class BookService {
	
	private ConcurrentMap<Long, Book> books = new ConcurrentHashMap<>();
	private AtomicLong nextId = new AtomicLong();
	
	public BookService() {
		try {
		save(new Book("Robert C. Martin", 
				"Noted software expert Robert C. Martin presents a revolutionary paradigm with Clean Code: A Handbook of Agile Software Craftsmanship. Martin has teamed up with his colleagues from Object Mentor to distill their best agile practice of cleaning code “on the fly” into a book that will instill within you the values of a software craftsman and make you a better programmer―but only if you work at it.",
				LocalDate.of(2008, 8, 1), 
				"Pearson", 
				"Clean Code: A Handbook of Agile Software Craftsmanship"));
		
			save(new Book("Erich Gamma",
					"The authors begin by describing what patterns are and how they can help you design object-oriented software. They then go on to systematically name, explain, evaluate, and catalog recurring designs in object-oriented systems. With Design Patterns as your guide, you will learn how these important patterns fit into the software development process, and how you can leverage them to solve your own design problems most efficiently.",
					LocalDate.of(1980, 1, 17), 
					"Addison Wesley", 
					"Design Patterns: Elements of Reusable Object-Oriented Software"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Collection<Book> findAll() {
		return books.values();
	}

	public Book findById(long id) {
		return books.get(id);
	}

	public void save(Book Book) {

		long id = nextId.getAndIncrement();

		Book.setId(id);
		
		this.books.put(id, Book);
	}

	public void deleteById(long id) {
		this.books.remove(id);
	}

}
