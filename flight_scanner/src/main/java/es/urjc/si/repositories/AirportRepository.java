package es.urjc.si.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.si.models.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {

}
