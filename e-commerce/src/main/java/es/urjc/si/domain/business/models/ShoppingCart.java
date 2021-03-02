package es.urjc.si.domain.business.models;

import java.util.ArrayList;
import java.util.UUID;

import es.urjc.si.domain.mappers.ShoppingCartMapper;
import es.urjc.si.domain.ports.IShoppingCartValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
	
	private long id;
	
	private String customer;
		
	private boolean finalized;
	
	private ArrayList<Order> orders;
	
	public boolean validate(IShoppingCartValidator shoppingCartValidator) {
		return shoppingCartValidator.validate(ShoppingCartMapper.map(this));
	}

}
