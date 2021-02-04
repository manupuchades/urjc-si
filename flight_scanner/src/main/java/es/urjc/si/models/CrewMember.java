package es.urjc.si.models;

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
public class CrewMember {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String employeeId;

	private String name;

	private String surname;
	
	private String job;

	private String company;

}
