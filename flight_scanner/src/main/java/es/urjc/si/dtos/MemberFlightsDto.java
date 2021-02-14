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
	
	private String firstName;

	private String lastName;
	
	private long numberOfFlights;
	
	private double flightTime; // Hours

}
