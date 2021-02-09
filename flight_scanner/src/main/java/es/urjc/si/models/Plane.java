package es.urjc.si.models;

import java.util.Collection;
import java.util.Collections;

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
@NoArgsConstructor
@AllArgsConstructor
public class Plane {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String reference;

	private String builder;

	private String model;

	private int flightHours;
	
	@OneToMany(mappedBy = "plane")
	@ToString.Exclude
	@Builder.Default
	private Collection<Review> reviews = Collections.emptyList();
	
	@OneToMany(mappedBy = "plane")
	@ToString.Exclude
	@Builder.Default
	private Collection<Flight> flights = Collections.emptyList();

}
