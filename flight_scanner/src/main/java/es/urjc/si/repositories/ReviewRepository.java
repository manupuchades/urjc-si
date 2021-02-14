package es.urjc.si.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.urjc.si.dtos.PlaneMechanicsDto;
import es.urjc.si.models.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	@Query(value = "select new es.urjc.si.dtos.PlaneMechanicsDto(p.reference, m.firstName, m.lastName) "
			+ "from Review r "
				+ "INNER JOIN Mechanic m on r.mechanic = m "
				+ "INNER JOIN Plane p on r.plane = p "
				+ "ORDER BY p.reference")
    List<PlaneMechanicsDto> findAllMechanicsForEveryPlane();
}
