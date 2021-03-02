package es.urjc.si.domain.business.use_cases.stubs;

import java.util.ArrayList;

import es.urjc.si.domain.dtos.product.FullProductDto;
import es.urjc.si.domain.dtos.shoppingCart.AddOrderCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.CreateShoppingCartCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.DeleteOrderCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.FullShoppingCartDto;
import es.urjc.si.domain.dtos.shoppingCart.OrderDto;
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
	public FullShoppingCartDto save(CreateShoppingCartCommandDto shoppingCart) {
		FullShoppingCartDto p = FullShoppingCartDto.builder().id(index).customer(shoppingCart.getCustomer()).finalized(false).build();
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
	public FullShoppingCartDto addOrder(AddOrderCommandDto orderDto) {
		
		FullShoppingCartDto sc = shoppingCarts.get((int) orderDto.getShoppingCartId());
		FullProductDto p = products.findById(orderDto.getProductId());
		
		int order_index = sc.getOrders().size();
		sc.getOrders().add(OrderDto.builder().id((long)order_index).product(p).quantity(orderDto.getQuantity()).build());
		
		return sc;
	}

	@Override
	public FullShoppingCartDto deleteOrder(DeleteOrderCommandDto orderDto) {
		FullShoppingCartDto sc = shoppingCarts.get((int) orderDto.getShoppingCartId());
		
		sc.getOrders().removeIf(o -> o.getProduct().getId() == orderDto.getProductId());
		
		return sc;
	}
}
