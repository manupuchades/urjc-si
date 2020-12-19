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
    
        User u1 = users.save(User.builder().email("user1@mail.com").nick("user1").build());
        User u2 = users.save(User.builder().email("user2@mail.com").nick("user2").build());
        User u3 = users.save(User.builder().email("user3@mail.com").nick("user3").build());
        User u4 = users.save(User.builder().email("user4@mail.com").nick("user4").build());
        
        Book b1 = books.save(Book.builder().author("Robert C. Martin").description("Martin has teamed up with his colleagues from Object Mentor to distill their best agile practice of cleaning code.")
				.edition(LocalDate.of(2008, 8, 1)).publisher("Pearson").title("Clean Code: A Handbook of Agile Software Craftsmanship").build());
        
        Book b2 = books.save(Book.builder().author("Erich Gamma").description("The authors begin by describing what patterns are and how they can help you design object-oriented software.")
        		.edition(LocalDate.of(1980, 1, 17)).publisher("Addison Wesley").title("Design Patterns: Elements of Reusable Object-Oriented Software").build());
		
        Book b3 = books.save(Book.builder().author("Martin Fowler").description("Experienced programmers worldwide have relied on Martin Fowler’s Refactoring to improve the design of existing code.")
        		.edition(LocalDate.of(2018, 11, 20)).publisher("Addison Wesley").title("Refactoring: Improving the Design of Existing Code").build());
        
        reviews.save(Review.builder().book(b1).comment("Excellent").rating(5).user(u4).build());
        reviews.save(Review.builder().book(b2).comment("Great book").rating(5).user(u1).build());
        reviews.save(Review.builder().book(b2).comment("Great book").rating(5).user(u2).build());
        reviews.save(Review.builder().book(b3).comment("Horrible").rating(2).user(u4).build());
        reviews.save(Review.builder().book(b3).comment("Not Bad").rating(3).user(u1).build());
        reviews.save(Review.builder().book(b3).comment("Surprising").rating(4).user(u2).build());
    }
}
