package es.urjc.si.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.si.exceptions.ReviewNotFoundException;
import es.urjc.si.models.Review;
import es.urjc.si.repositories.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	ReviewRepository reviewRepository;

	public Collection<Review> findAll(long book_id) {
		return reviewRepository.findAll(book_id);
	}

	public Review findById(long id) {
		return reviewRepository.findById(id).orElseThrow(ReviewNotFoundException::new);
	}
	
	public Review save(Review review) {
		this.bookService.findById(review.getBook_id());
		return reviewRepository.save(review);
	}

	public Review deleteById(long id) {
		return this.reviewRepository.deleteById(id).orElseThrow(ReviewNotFoundException::new);
	}
}
