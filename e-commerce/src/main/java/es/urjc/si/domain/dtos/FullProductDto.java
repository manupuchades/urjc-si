package es.urjc.si.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FullProductDto {

	private long id;

	private String name;

	private String description;

	private double price;
}
