package es.urjc.si.app.ports;

import es.urjc.si.domain.dtos.shoppingCart.AddOrderCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.CreateShoppingCartCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.DeleteOrderCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.DeleteShoppingCartCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.FinalizeShoppingCartCommandDto;

public interface IShoppingCartCommandPublisher {
	
	void createShoppingCart(CreateShoppingCartCommandDto createShoppingCartDto);

	void deleteShoppingCart(DeleteShoppingCartCommandDto deleteShoppingCartDto);

	void finalizeShoppingCart(FinalizeShoppingCartCommandDto finalizeShoppingCartDto);

	void addOrder(AddOrderCommandDto addOrderDto);

	void deleteOrder(DeleteOrderCommandDto deleteOrderDto);

}
