package es.urjc.si.infra.db.h2;

import org.springframework.stereotype.Component;

import es.urjc.si.domain.dtos.shoppingCart.AddOrderCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.CreateShoppingCartCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.DeleteOrderCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.FullShoppingCartDto;
import es.urjc.si.domain.exceptions.ProductNotFoundException;
import es.urjc.si.domain.exceptions.ShoppingCartNotFoundException;
import es.urjc.si.domain.ports.IShoppingCartRepository;
import es.urjc.si.infra.db.h2.entities.Order;
import es.urjc.si.infra.db.h2.entities.Product;
import es.urjc.si.infra.db.h2.entities.ShoppingCart;
import es.urjc.si.infra.db.h2.mappers.ShoppingCartMapper;
import es.urjc.si.infra.db.h2.repositories.ProductRepository;
import es.urjc.si.infra.db.h2.repositories.ShoppingCartRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ShoppingCartRepositoryAdapter implements IShoppingCartRepository{
	
	private ShoppingCartRepository shoppingCartRepository;
	
	private ProductRepository productRepository;


	@Override
	public FullShoppingCartDto findById(long id) {
		FullShoppingCartDto sc = shoppingCartRepository.findById(id).map(ShoppingCartMapper::map).orElseThrow(ShoppingCartNotFoundException::new);
		
		return sc;
	}

	@Override
	public FullShoppingCartDto save(CreateShoppingCartCommandDto shoppingCartInputDto) {
		return ShoppingCartMapper.map(shoppingCartRepository.save(ShoppingCartMapper.map(shoppingCartInputDto)));
	}

	@Override
	public FullShoppingCartDto delete(long id) {
		FullShoppingCartDto sc = findById(id);
		shoppingCartRepository.deleteById(id);
		return sc;
	}

	@Override
	public FullShoppingCartDto finalize(long id) {
		ShoppingCart sc = shoppingCartRepository.findById(id).orElseThrow(ShoppingCartNotFoundException::new);
		sc.setFinalized(true);
		return ShoppingCartMapper.map(shoppingCartRepository.save(sc));
	}

	@Override
	public FullShoppingCartDto addOrder(AddOrderCommandDto orderDto) {
		ShoppingCart sc = shoppingCartRepository.findById(orderDto.getShoppingCartId()).orElseThrow(ShoppingCartNotFoundException::new);
		Product p = productRepository.findById(orderDto.getProductId()).orElseThrow(ProductNotFoundException::new);
		sc.getProduct_orders().removeIf(o -> o.getProduct().getId() == orderDto.getProductId());
		sc.getProduct_orders().add(Order.builder().shoppingCart(sc).product(p).order_quantity(orderDto.getQuantity()).build());
				
		return ShoppingCartMapper.map(shoppingCartRepository.save(sc));
	}

	@Override
	public FullShoppingCartDto deleteOrder(DeleteOrderCommandDto orderDto) {
		ShoppingCart sc = shoppingCartRepository.findById(orderDto.getShoppingCartId()).orElseThrow(ShoppingCartNotFoundException::new);
		
		if(!sc.getProduct_orders().removeIf(o -> o.getProduct().getId() == orderDto.getProductId())) {
			throw new ProductNotFoundException();
		}

		return ShoppingCartMapper.map(shoppingCartRepository.save(sc));
	}

}
