package es.urjc.si.models;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CrewMember {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String employeeId;

	private String memberName;

	private String surname;
	
	private String job;

	private String company;
	
    @OneToMany(mappedBy = "crewMember", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@Builder.Default
	private Collection<FlightCrew> flights = Collections.emptyList();

}
