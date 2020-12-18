package es.urjc.si.services;

import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.si.models.Book;
import es.urjc.si.models.Review;
import es.urjc.si.models.User;

@Service
public class DBPopulator {

	@Autowired
	BookService books;
	
	@Autowired
	UserService users;
	
	
	@Autowired
	ReviewService reviews;
	
    @PostConstruct
    public void populate() {
        User u1 = users.save(new User("user1@mail.com", "user1"));
        User u2 = users.save(new User("user2@mail.com", "user2"));
        User u3 = users.save(new User("user3@mail.com", "user3"));
        User u4 = users.save(new User("user4@mail.com", "user4"));

        
        
        Book b1 = books.save(new Book("Robert C. Martin",
				"Martin has teamed up with his colleagues from Object Mentor to distill their best agile practice of cleaning code.",
				LocalDate.of(2008, 8, 1), "Pearson", "Clean Code: A Handbook of Agile Software Craftsmanship"));
        
        System.out.println(b1);

        Book b2 = books.save(new Book("Erich Gamma",
				"The authors begin by describing what patterns are and how they can help you design object-oriented software.",
				LocalDate.of(1980, 1, 17), "Addison Wesley",
				"Design Patterns: Elements of Reusable Object-Oriented Software"));
		
        Book b3 = books.save(new Book("Martin Fowler",
				"Experienced programmers worldwide have relied on Martin Fowler’s Refactoring to improve the design of existing code.",
				LocalDate.of(2018, 11, 20), "Addison Wesley",
				"Refactoring: Improving the Design of Existing Code"));
        
        reviews.save(new Review(b1, "Excellent", 5, u4));
        reviews.save(new Review(b2, "Great book", 5, u1));
        reviews.save(new Review(b2, "Great book", 5, u2));
        reviews.save(new Review(b3, "Horrible", 2, u4));
        reviews.save(new Review(b3, "Not Bad", 3, u1));
        reviews.save(new Review(b3, "Surprising", 4, u2));
    }
	
}
