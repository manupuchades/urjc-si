package es.urjc.si.app.rest.dtos.requests;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

	@NotNull
	private String name;

	@NotNull
	private String description;
	
	@NotNull
	private double price;

}
