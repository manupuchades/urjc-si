package es.urjc.si.domain.business.use_cases.stubs;

import java.util.ArrayList;

import es.urjc.si.domain.dtos.FullProductDto;
import es.urjc.si.domain.dtos.FullShoppingCartDto;
import es.urjc.si.domain.dtos.OrderDto;
import es.urjc.si.domain.dtos.OrderInputDto;
import es.urjc.si.domain.dtos.ShoppingCartInputDto;
import es.urjc.si.domain.exceptions.ShoppingCartNotFoundException;
import es.urjc.si.domain.ports.IProductRepository;
import es.urjc.si.domain.ports.IShoppingCartRepository;

public class ShoppingCartRepositoryStub implements IShoppingCartRepository{
	
	ArrayList<FullShoppingCartDto> shoppingCarts;

	IProductRepository products;
	
	int index;

	public ShoppingCartRepositoryStub(IProductRepository products) {
		this.products = products;
		shoppingCarts = new ArrayList<>();
		index = 0;
	}

	@Override
	public FullShoppingCartDto findById(long id) {
		try {
			return shoppingCarts.get((int) id);
		} catch (IndexOutOfBoundsException e) {
			throw new ShoppingCartNotFoundException();
		}
	}

	@Override
	public FullShoppingCartDto save(ShoppingCartInputDto shoppingCart) {
		FullShoppingCartDto p = FullShoppingCartDto.builder().id(Long.valueOf(index)).customer(shoppingCart.getCustomer()).finalized(false).build();
		shoppingCarts.add(index, p);
		index++;

		return p;
	}

	@Override
	public FullShoppingCartDto delete(long id) {
		return shoppingCarts.remove((int) id);
	}

	@Override
	public FullShoppingCartDto finalize(long id) {
		FullShoppingCartDto shoppingCart = shoppingCarts.get((int) id);
		shoppingCart.setFinalized(true);
		
		return shoppingCart;
	}

	@Override
	public FullShoppingCartDto addOrder(OrderInputDto orderDto) {
		
		FullShoppingCartDto sc = shoppingCarts.get((int) orderDto.getShoppingCartId());
		FullProductDto p = products.findById(orderDto.getProductId());
		
		int order_index = sc.getOrders().size();
		sc.getOrders().add(OrderDto.builder().id((long)order_index).product(p).quantity(orderDto.getQuantity()).build());
		
		return sc;
	}

	@Override
	public FullShoppingCartDto deleteOrder(OrderInputDto orderDto) {
		FullShoppingCartDto sc = shoppingCarts.get((int) orderDto.getShoppingCartId());
		
		sc.getOrders().removeIf(o -> o.getProduct().getId() == orderDto.getProductId());
		
		return sc;
	}
}
