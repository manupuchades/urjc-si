package es.urjc.si.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.si.models.Plane;

public interface PlaneRepository extends JpaRepository<Plane, Long> {
	
}
