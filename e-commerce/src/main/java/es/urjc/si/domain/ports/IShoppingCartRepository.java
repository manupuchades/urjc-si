package es.urjc.si.domain.ports;

import es.urjc.si.domain.dtos.shoppingCart.AddOrderCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.CreateShoppingCartCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.DeleteOrderCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.FullShoppingCartDto;

public interface IShoppingCartRepository {
	
	FullShoppingCartDto findById(long id);
	
	FullShoppingCartDto save(CreateShoppingCartCommandDto shoppingCartInputDto);
	
	FullShoppingCartDto delete(long id);
	
	FullShoppingCartDto finalize(long id);
	
	FullShoppingCartDto addOrder(AddOrderCommandDto orderDto);
	
	FullShoppingCartDto deleteOrder(DeleteOrderCommandDto orderDto);

}
