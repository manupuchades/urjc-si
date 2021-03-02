package es.urjc.si.domain.mappers;

import es.urjc.si.domain.business.models.ShoppingCart;
import es.urjc.si.domain.dtos.shoppingCart.ShoppingCartCompletedDto;

public class ShoppingCartExpenditureMapper {

	public static ShoppingCartCompletedDto map(ShoppingCart shoppingCart) {
		return ShoppingCartCompletedDto.builder().build();
	}
	
}
