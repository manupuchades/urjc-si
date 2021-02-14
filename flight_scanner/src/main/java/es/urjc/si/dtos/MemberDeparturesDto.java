package es.urjc.si.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDeparturesDto {
	
	private String firstName;

	private String lastName;
	
	private String city;
	
	private LocalDateTime date;

}
