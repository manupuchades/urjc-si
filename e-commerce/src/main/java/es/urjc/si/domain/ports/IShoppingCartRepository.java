package es.urjc.si.domain.ports;

import es.urjc.si.domain.dtos.ShoppingCartDto;
import es.urjc.si.domain.dtos.ShoppingCartInputDto;

public interface IShoppingCartRepository {
	
	ShoppingCartDto findById(long id);
	
	ShoppingCartDto save(ShoppingCartInputDto product);
	
	ShoppingCartDto delete(long id);
	
	ShoppingCartDto finalize(long id);

}
