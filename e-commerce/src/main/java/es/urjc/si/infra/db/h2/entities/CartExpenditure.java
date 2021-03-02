package es.urjc.si.infra.db.h2.entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CART_EXPENDITURE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartExpenditure {
	
	@Id
	private long id;
		
	private double expenditure;

}
