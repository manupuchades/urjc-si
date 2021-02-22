package es.urjc.si.domain.business.use_cases;

import java.util.ArrayList;

import es.urjc.si.domain.dtos.ShoppingCartDto;
import es.urjc.si.domain.dtos.ShoppingCartInputDto;
import es.urjc.si.domain.exceptions.ShoppingCartNotFoundException;
import es.urjc.si.domain.ports.IShoppingCartRepository;

public class ShoppingCartRepositoryStub implements IShoppingCartRepository{
	ArrayList<ShoppingCartDto> shoppingCarts;

	int index;

	ShoppingCartRepositoryStub() {
		shoppingCarts = new ArrayList<>();
		index = 0;
	}

	@Override
	public ShoppingCartDto findById(long id) {
		try {
			return shoppingCarts.get((int) id);
		} catch (IndexOutOfBoundsException e) {
			throw new ShoppingCartNotFoundException();
		}
	}

	@Override
	public ShoppingCartDto save(ShoppingCartInputDto shoppingCart) {
		ShoppingCartDto p = ShoppingCartDto.builder().id(Long.valueOf(index)).customer(shoppingCart.getCustomer()).amount(0).finalized(false).build();
		shoppingCarts.add(index, p);
		index++;

		return p;
	}

	@Override
	public ShoppingCartDto delete(long id) {
		return shoppingCarts.remove((int) id);
	}

	@Override
	public ShoppingCartDto finalize(long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
