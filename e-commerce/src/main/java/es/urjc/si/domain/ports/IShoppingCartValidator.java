package es.urjc.si.domain.ports;

import es.urjc.si.domain.dtos.shoppingCart.FullShoppingCartDto;

public interface IShoppingCartValidator {

	Boolean validate(FullShoppingCartDto shoppingCart);
}
