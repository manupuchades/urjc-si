package es.urjc.si.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberFlightsDto {
	
	private String name;

	private String surname;
	
	private long numberOfFlights;
	
	private double flightTime; // Hours

}
