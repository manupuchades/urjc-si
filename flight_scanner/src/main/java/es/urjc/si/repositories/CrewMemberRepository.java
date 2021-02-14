/**
 * 
 */
package es.urjc.si.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.urjc.si.dtos.MemberDeparturesDto;
import es.urjc.si.dtos.MemberFlightsDto;
import es.urjc.si.models.CrewMember;

public interface CrewMemberRepository extends JpaRepository<CrewMember, Long> {

	@Query(value = "select new es.urjc.si.dtos.MemberDeparturesDto(cm.firstName, cm.lastName, a.city, f.departureDateTime) "
			+ "from CrewMember cm "
				+ "INNER JOIN FlightCrew fc on cm = fc.crewMember "
				+ "INNER JOIN Flight f on fc.flight = f "
				+ "INNER JOIN Airport a on f.departure = a "
				+ "WHERE cm.employeeId = :employeeId ")
    List<MemberDeparturesDto> getMemberDepartures(@Param("employeeId") String employeeId);
	
	@Query(value = "select new es.urjc.si.dtos.MemberFlightsDto(cm.firstName, cm.lastName, count(f), sum(f.flightDuration)) "
			+ "from CrewMember cm "
				+ "INNER JOIN FlightCrew fc on cm = fc.crewMember "
				+ "INNER JOIN Flight f on fc.flight = f "
				+ "GROUP BY cm.firstName, cm.lastName")
    List<MemberFlightsDto> findMembersFlightExperience();
	
}
