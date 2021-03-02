package es.urjc.si.domain.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductCommandDto {
		
	private String name;
	
	private String description;

	private double price;
}
