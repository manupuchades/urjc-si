package es.urjc.si.dtos;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProvinciasPorComunidadDto {
	
	  @Field("_id")
	  private String comunidad;

	  @Field("provincias")
	  private Long numProvincias;

}
