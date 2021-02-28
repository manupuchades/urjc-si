package es.urjc.si.domain.ports;

import es.urjc.si.domain.dtos.FullShoppingCartDto;

public interface IShoppingCartValidator {

	Boolean validate(FullShoppingCartDto shoppingCart);
}
