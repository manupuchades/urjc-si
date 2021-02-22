package es.urjc.si.domain.business.use_cases;

import es.urjc.si.domain.dtos.ShoppingCartDto;
import es.urjc.si.domain.dtos.ShoppingCartInputDto;
import es.urjc.si.domain.ports.IShoppingCartRepository;
import es.urjc.si.domain.services.IShoppingCartService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShoppingCartUseCase implements IShoppingCartService {

	IShoppingCartRepository shoppingCartRepository;

	@Override
	public ShoppingCartDto findById(long id) {
		return shoppingCartRepository.findById(id);
	}

	@Override
	public ShoppingCartDto save(ShoppingCartInputDto productDto) {
		return shoppingCartRepository.save(productDto);
	}

	@Override
	public ShoppingCartDto delete(long id) {
		return shoppingCartRepository.delete(id);
	}

}
