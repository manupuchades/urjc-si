package es.urjc.si.domain.services;

import es.urjc.si.domain.dtos.FullShoppingCartDto;
import es.urjc.si.domain.dtos.OrderInputDto;
import es.urjc.si.domain.dtos.ShoppingCartInputDto;

public interface IShoppingCartService {

	FullShoppingCartDto findById(long id);

	FullShoppingCartDto save(ShoppingCartInputDto productDto);

	FullShoppingCartDto delete(long id);

	FullShoppingCartDto finalize(long id);

	FullShoppingCartDto addOrder(OrderInputDto orderDto);

	FullShoppingCartDto deleteOrder(OrderInputDto orderDto);
}
