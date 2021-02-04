package es.urjc.si.models;

import java.time.LocalDate;
import java.time.Period;

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
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Mechanic mechanic;

	private LocalDate start;
	
	private Period duration;

	private LocalDate end;
	
	private Plane plane;
	
	private String type;
	
	private String description;

	private Airport airport;
	
}
