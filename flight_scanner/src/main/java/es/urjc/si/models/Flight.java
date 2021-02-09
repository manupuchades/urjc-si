package es.urjc.si.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String flightId;

	private String company;

	@ManyToOne
	private Plane plane;

	@ManyToOne
	private Airport departure;

	@ManyToOne
	private Airport arrival;

	private LocalDateTime departureDateTime;

	private double flightDuration; // Hours

	@OneToMany(mappedBy = "flight", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@Builder.Default
	private Collection<FlightCrew> crewMembers = Collections.emptyList();

}
