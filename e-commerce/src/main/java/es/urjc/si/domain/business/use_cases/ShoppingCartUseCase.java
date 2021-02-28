package es.urjc.si.domain.business.use_cases;

import es.urjc.si.domain.dtos.FullShoppingCartDto;
import es.urjc.si.domain.dtos.OrderInputDto;
import es.urjc.si.domain.dtos.ShoppingCartInputDto;
import es.urjc.si.domain.exceptions.InvalidShoppingCartException;
import es.urjc.si.domain.ports.IProductRepository;
import es.urjc.si.domain.ports.IShoppingCartRepository;
import es.urjc.si.domain.ports.IShoppingCartValidator;
import es.urjc.si.domain.services.IShoppingCartService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShoppingCartUseCase implements IShoppingCartService {

	IShoppingCartRepository shoppingCartRepository;
	
	IProductRepository productRepository;
	
	IShoppingCartValidator shoppingCartValidator;

	@Override
	public FullShoppingCartDto findById(long id) {
		return shoppingCartRepository.findById(id);
	}

	@Override
	public FullShoppingCartDto save(ShoppingCartInputDto productDto) {
		return shoppingCartRepository.save(productDto);
	}

	@Override
	public FullShoppingCartDto delete(long id) {
		return shoppingCartRepository.delete(id);
	}

	@Override
	public FullShoppingCartDto finalize(long id) {
		FullShoppingCartDto shoppingCart = shoppingCartRepository.findById(id);
		
		if(shoppingCartValidator.validate(shoppingCart)) {
			return shoppingCartRepository.finalize(id);
		}
		
		throw new InvalidShoppingCartException(shoppingCart.toString());
	}

	@Override
	public FullShoppingCartDto addOrder(OrderInputDto orderDto) {
		return shoppingCartRepository.addOrder(orderDto);
	}

	@Override
	public FullShoppingCartDto deleteOrder(OrderInputDto orderDto) {
		return shoppingCartRepository.deleteOrder(orderDto);
	}

}
