package es.urjc.si.domain.ports;

import es.urjc.si.domain.dtos.FullShoppingCartDto;
import es.urjc.si.domain.dtos.OrderInputDto;
import es.urjc.si.domain.dtos.ShoppingCartInputDto;

public interface IShoppingCartRepository {
	
	FullShoppingCartDto findById(long id);
	
	FullShoppingCartDto save(ShoppingCartInputDto shoppingCartInputDto);
	
	FullShoppingCartDto delete(long id);
	
	FullShoppingCartDto finalize(long id);
	
	FullShoppingCartDto addOrder(OrderInputDto orderDto);
	
	FullShoppingCartDto deleteOrder(OrderInputDto orderDto);

}
