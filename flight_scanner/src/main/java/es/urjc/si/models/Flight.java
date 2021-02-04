package es.urjc.si.models;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Builder
@ToString
public class Flight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String flightId;

	private String company;

	private Plane plane;
	
	private Airport departure;
	
	private Airport destination;
	
	private LocalDateTime departureTime;
	
	private Period duration;
	
	private List<CrewMember> crew;


}
