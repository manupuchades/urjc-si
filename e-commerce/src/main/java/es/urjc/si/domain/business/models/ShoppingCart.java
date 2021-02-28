package es.urjc.si.domain.business.models;

import java.util.Collection;
import java.util.Collections;

import es.urjc.si.infra.db.h2.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
	
	private Long id;
	
	private String customer;
		
	private boolean finalized;
	
	private Collection<Order> orders = Collections.emptyList();

}
