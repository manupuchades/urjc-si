package es.urjc.si.domain.services;

import es.urjc.si.domain.dtos.shoppingCart.AddOrderCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.CreateShoppingCartCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.DeleteOrderCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.FullShoppingCartDto;

public interface IShoppingCartService {

	FullShoppingCartDto findById(long id);

	FullShoppingCartDto save(CreateShoppingCartCommandDto productDto);

	FullShoppingCartDto delete(long id);

	FullShoppingCartDto finalize(long id);

	FullShoppingCartDto addOrder(AddOrderCommandDto orderDto);

	FullShoppingCartDto deleteOrder(DeleteOrderCommandDto orderDto);
}
