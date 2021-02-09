package es.urjc.si.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.urjc.si.dtos.FlightArrivalsDto;
import es.urjc.si.models.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

	@Query(value = "select new es.urjc.si.dtos.FlightArrivalsDto(f.flightId, f.departureDateTime) "
			+ "from Flight f "
				+ "INNER JOIN Airport a on f.arrival = a "
				+ "WHERE a.city = :destination "
				+ "AND DATE(f.departureDateTime) = DATE(:date) "
				+ "ORDER BY f.departureDateTime asc")
    List<FlightArrivalsDto> getDestinationArrivals(@Param("destination") String destination, @Param("date") LocalDate date);
}
