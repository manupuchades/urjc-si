package es.urjc.si.domain.business.use_cases.stubs;

import es.urjc.si.domain.dtos.shoppingCart.ShoppingCartCompletedDto;
import es.urjc.si.domain.ports.IShoppingCartPublisher;

public class ShoppingCartPublisherStub implements IShoppingCartPublisher{

	@Override
	public void publishCartCompleted(ShoppingCartCompletedDto shoppingCart) {
		// Do nothing		
	}

}
