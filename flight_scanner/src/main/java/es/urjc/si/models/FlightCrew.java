package es.urjc.si.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class FlightCrew {

	@EmbeddedId
	private FlightCrewId id;

	@ManyToOne
	@MapsId("memberId")
	private CrewMember crewMember;

	@ManyToOne
	@MapsId("flightId")
	private Flight flight;

	public FlightCrew(Flight flight, CrewMember crewMember) {
		this.crewMember = crewMember;
		this.flight = flight;

		this.id = new FlightCrewId(flight.getId(), crewMember.getId());
	}

}
