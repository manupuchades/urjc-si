package es.urjc.si.domain.business.use_cases;

import es.urjc.si.domain.business.models.ShoppingCart;
import es.urjc.si.domain.dtos.shoppingCart.AddOrderCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.CreateShoppingCartCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.DeleteOrderCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.FullShoppingCartDto;
import es.urjc.si.domain.exceptions.InvalidShoppingCartException;
import es.urjc.si.domain.mappers.ShoppingCartExpenditureMapper;
import es.urjc.si.domain.mappers.ShoppingCartMapper;
import es.urjc.si.domain.ports.IProductRepository;
import es.urjc.si.domain.ports.IShoppingCartPublisher;
import es.urjc.si.domain.ports.IShoppingCartRepository;
import es.urjc.si.domain.ports.IShoppingCartValidator;
import es.urjc.si.domain.services.IShoppingCartService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShoppingCartUseCase implements IShoppingCartService {

	IShoppingCartRepository shoppingCartRepository;
	
	IProductRepository productRepository;
	
	IShoppingCartValidator shoppingCartValidator;
	
	IShoppingCartPublisher shoppingCartPublisher;

	@Override
	public FullShoppingCartDto findById(long id) {
		return shoppingCartRepository.findById(id);
	}

	@Override
	public FullShoppingCartDto save(CreateShoppingCartCommandDto productDto) {
		return shoppingCartRepository.save(productDto);
	}

	@Override
	public FullShoppingCartDto delete(long id) {
		return shoppingCartRepository.delete(id);
	}

	@Override
	public FullShoppingCartDto finalize(long id) {
		ShoppingCart shoppingCart = ShoppingCartMapper.map(shoppingCartRepository.findById(id));
		
		if(shoppingCart.validate(shoppingCartValidator)) {
			FullShoppingCartDto finalizedShoppingCart = shoppingCartRepository.finalize(id);
			shoppingCartPublisher.publishCartCompleted(ShoppingCartExpenditureMapper.map(shoppingCart));
			return finalizedShoppingCart;
		}
		
		throw new InvalidShoppingCartException(shoppingCart.toString());
	}

	@Override
	public FullShoppingCartDto addOrder(AddOrderCommandDto orderDto) {
		return shoppingCartRepository.addOrder(orderDto);
	}

	@Override
	public FullShoppingCartDto deleteOrder(DeleteOrderCommandDto orderDto) {
		return shoppingCartRepository.deleteOrder(orderDto);
	}

}
