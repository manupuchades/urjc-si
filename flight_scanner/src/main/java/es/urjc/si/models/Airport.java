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
@NoArgsConstructor
@AllArgsConstructor
public class Airport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String iata;

	private String name;

	private String city;

	private String country;

	@OneToMany(mappedBy = "airport", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	@ToString.Exclude
	private Collection<Review> reviews = Collections.emptyList();
	
	@OneToMany(mappedBy = "departure", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	@ToString.Exclude
	private Collection<Flight> departures = Collections.emptyList();
	
	@OneToMany(mappedBy = "arrival", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	@ToString.Exclude
	private Collection<Flight> arrivals = Collections.emptyList();

}
