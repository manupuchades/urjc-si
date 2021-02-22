package es.urjc.si.domain.services;

import es.urjc.si.domain.dtos.ShoppingCartDto;
import es.urjc.si.domain.dtos.ShoppingCartInputDto;

public interface IShoppingCartService {
	ShoppingCartDto findById(long id);
	
	ShoppingCartDto save(ShoppingCartInputDto productDto);
	
	ShoppingCartDto delete(long id);
}
