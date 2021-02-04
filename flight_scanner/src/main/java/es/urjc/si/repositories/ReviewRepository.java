package es.urjc.si.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.si.models.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
