package es.urjc.si.domain.business.use_cases.stubs;

import es.urjc.si.domain.dtos.shoppingCart.FullShoppingCartDto;
import es.urjc.si.domain.ports.IShoppingCartValidator;

public class ShoppingCartValidatorStub implements IShoppingCartValidator{

	@Override
	public Boolean validate(FullShoppingCartDto shoppingCart) {
		return true;
	}

}
