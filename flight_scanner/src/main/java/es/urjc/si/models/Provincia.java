package es.urjc.si.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Provincia {
	
	  @Id
	  private String id;

	  @Field("Nombre")
	  private String nombre;

	  @Field("CA")
	  private String ca;

	  @Field("Superficie")
	  private int superficie;

	  @Field("Datos")
	  private List<Dato> datos;

}
