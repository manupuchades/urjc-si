package es.urjc.si.domain.ports;

import es.urjc.si.domain.dtos.shoppingCart.ShoppingCartCompletedDto;

public interface IShoppingCartPublisher {
	
	void publishCartCompleted(ShoppingCartCompletedDto shoppingCart);

}
