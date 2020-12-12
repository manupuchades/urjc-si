package es.urjc.si.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import es.urjc.si.models.Review;

@Service
public class ReviewService {
	
	private ConcurrentMap<Long, Review> reviews = new ConcurrentHashMap<>();
	private AtomicLong nextId = new AtomicLong();

	public ReviewService() {
		save(new Review(0, "Excellent", 5, "User0"));
		save(new Review(0, "Great book", 5, "User1"));
		save(new Review(1, "Horrible", 2, "User0"));
	}

	public Collection<Review> findAll(long book_id) {
		return reviews.values().stream().filter(review -> book_id == review.getBook_id()).collect(Collectors.toCollection(()-> new ArrayList<Review>()));
	}

	public Review findById(long id) {
		return reviews.get(id);
	}
	
	public Review save(Review review) {

		long id = nextId.getAndIncrement();		
		review.setId(id);
		this.reviews.put(id, review);
		
		return review;
	}

	public void deleteById(long id) {
		this.reviews.remove(id);
	}
}
