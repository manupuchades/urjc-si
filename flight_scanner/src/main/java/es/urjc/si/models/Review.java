package es.urjc.si.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Plane plane;
	
	private LocalDate startDate;
	
	private LocalDate endDate;

	private Double reviewDuration; // hours
	
	@ManyToOne
	private Mechanic mechanic;
	
	private String reviewType;
	
	private String description;
	
	@ManyToOne
	private Airport airport;
	
}
