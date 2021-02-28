package es.urjc.si.infra.ext;

import java.util.Random;

import org.springframework.stereotype.Component;

import es.urjc.si.domain.dtos.FullShoppingCartDto;
import es.urjc.si.domain.ports.IShoppingCartValidator;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class ShoppingCartValidatorAdapter implements IShoppingCartValidator{

	@Override
	public Boolean validate(FullShoppingCartDto shoppingCart) {
		Random rd = new Random();
		return rd.nextBoolean();
	}

}
