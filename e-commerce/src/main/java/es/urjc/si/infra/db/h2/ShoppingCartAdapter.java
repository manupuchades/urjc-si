package es.urjc.si.infra.db.h2;

import org.springframework.stereotype.Component;

import es.urjc.si.domain.dtos.FullShoppingCartDto;
import es.urjc.si.domain.dtos.OrderInputDto;
import es.urjc.si.domain.dtos.ShoppingCartInputDto;
import es.urjc.si.domain.exceptions.ProductNotFoundException;
import es.urjc.si.domain.exceptions.ShoppingCartNotFoundException;
import es.urjc.si.domain.ports.IShoppingCartRepository;
import es.urjc.si.infra.db.h2.entities.Order;
import es.urjc.si.infra.db.h2.entities.Product;
import es.urjc.si.infra.db.h2.entities.ShoppingCart;
import es.urjc.si.infra.db.h2.mappers.ShoppingCartMapper;
import es.urjc.si.infra.db.h2.repositories.OrderRepository;
import es.urjc.si.infra.db.h2.repositories.ProductRepository;
import es.urjc.si.infra.db.h2.repositories.ShoppingCartRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ShoppingCartAdapter implements IShoppingCartRepository{
	
	private OrderRepository orderRepository;
	
	private ShoppingCartRepository shoppingCartRepository;
	
	private ProductRepository productRepository;


	@Override
	public FullShoppingCartDto findById(long id) {
		FullShoppingCartDto sc = shoppingCartRepository.findById(id).map(ShoppingCartMapper::map).orElseThrow(ShoppingCartNotFoundException::new);
		
		return sc;
	}

	@Override
	public FullShoppingCartDto save(ShoppingCartInputDto shoppingCartInputDto) {
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
	public FullShoppingCartDto addOrder(OrderInputDto orderDto) {
		ShoppingCart sc = shoppingCartRepository.findById(orderDto.getShoppingCartId()).orElseThrow(ShoppingCartNotFoundException::new);
		Product p = productRepository.findById(orderDto.getProductId()).orElseThrow(ProductNotFoundException::new);
		sc.getProduct_orders().removeIf(o -> o.getProduct().getId() == orderDto.getProductId());
		sc.getProduct_orders().add(Order.builder().shoppingCart(sc).product(p).order_quantity(orderDto.getQuantity()).build());
				
		return ShoppingCartMapper.map(shoppingCartRepository.save(sc));
	}

	@Override
	public FullShoppingCartDto deleteOrder(OrderInputDto orderDto) {
		ShoppingCart sc = shoppingCartRepository.findById(orderDto.getShoppingCartId()).orElseThrow(ShoppingCartNotFoundException::new);
		
		if(!sc.getProduct_orders().removeIf(o -> o.getProduct().getId() == orderDto.getProductId())) {
			throw new ProductNotFoundException();
		}

		return ShoppingCartMapper.map(shoppingCartRepository.save(sc));
	}

}
