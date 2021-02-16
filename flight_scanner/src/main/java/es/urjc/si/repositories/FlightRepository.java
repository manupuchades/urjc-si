package es.urjc.si.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.urjc.si.dtos.FlightArrivalsDto;
import es.urjc.si.dtos.MemberFlightsInterface;
import es.urjc.si.models.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

	@Query(value = "select new es.urjc.si.dtos.FlightArrivalsDto(f.flightId, f.departureDateTime) "
			+ "from Flight f "
				+ "INNER JOIN Airport a on f.arrival = a "
				+ "WHERE a.city = :destination "
				+ "AND DATE(f.departureDateTime) = DATE(:date) "
				+ "ORDER BY f.departureDateTime asc")
    List<FlightArrivalsDto> getDestinationArrivals(@Param("destination") String destination, @Param("date") LocalDate date);
	
	
	@Query(value = "select cm.first_name as firstName, cm.last_name as lastName, count(f.id) as numberOfFlights, sum(f.flight_duration) as flightTime "
			+ "from flight f, JSON_TABLE (f.members, \"$[*]\" COLUMNS (m_id INT PATH \"$\")) as c_member "
				+ "JOIN crew_member cm on cm.id = c_member.m_id "
				+ "GROUP BY cm.first_name, cm.last_name;", nativeQuery = true)
    List<MemberFlightsInterface> findMembersFlightExperience();
}
