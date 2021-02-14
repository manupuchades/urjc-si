package es.urjc.si.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlaneMechanicsDto {
	
	private String planeReference;

	private String firstName;

	private String lastName;

}
