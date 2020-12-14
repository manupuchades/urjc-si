package es.urjc.si.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.si.models.Book;
import es.urjc.si.models.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
	
	List<Review> findByBook(Book book);
    
	List<Review> findByUserId(Long id);
}
