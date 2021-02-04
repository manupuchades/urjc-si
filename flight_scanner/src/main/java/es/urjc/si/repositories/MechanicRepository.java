package es.urjc.si.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.si.models.Mechanic;

public interface MechanicRepository extends JpaRepository<Mechanic, Long> {

}
