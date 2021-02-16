package es.urjc.si.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.urjc.si.dtos.PlaneMechanicsInterface;
import es.urjc.si.models.Plane;

public interface PlaneRepository extends JpaRepository<Plane, Long> {
	
	@Query(value = "select p.reference as planeReference, m.first_name as firstName, m.last_name as lastName "
			+ "from plane p, JSON_TABLE (p.review_details, \"$[*]\" COLUMNS (m_id INT PATH \"$.id\")) as p_mechanic "
				+ "JOIN mechanic m on m.id = p_mechanic.m_id "
				+ "GROUP BY m.first_name, m.last_name;", nativeQuery = true)
    List<PlaneMechanicsInterface> findAllMechanicsForEveryPlane();
	
}
