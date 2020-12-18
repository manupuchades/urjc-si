package es.urjc.si.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.si.exceptions.ReviewNotFoundException;
import es.urjc.si.models.Book;
import es.urjc.si.models.Review;
import es.urjc.si.models.User;
import es.urjc.si.repositories.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ReviewRepository reviewRepository;

	public Review findById(long id) {
		return reviewRepository.findById(id).orElseThrow(ReviewNotFoundException::new);
	}
	
	public Review save(Review review) {
		Book book = bookService.findById(review.getBook().getId());
		User user = userService.findByNick(review.getUser().getNick());
		
		review.setBook(book);
		review.setUser(user);
		
		return reviewRepository.save(review);
	}

	public Review deleteById(long id) {
		Review review = reviewRepository.findById(id).orElseThrow(ReviewNotFoundException::new);
		this.reviewRepository.deleteById(id);
		return review;
	}
}
