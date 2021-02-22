package es.urjc.si.domain.dtos;

import java.util.Collection;

import es.urjc.si.infra.db.h2.entities.ProductOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDto {
	
	private Long id;
	
	private String customer;
	
	private double amount;
	
	private boolean finalized;
	
	private Collection<ProductOrder> orders;

}
