package es.urjc.si.models;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dato {
	
	  @Field("Anyo")
	  private String anyo;

	  @Field("Valor")
	  private String valor;
}
