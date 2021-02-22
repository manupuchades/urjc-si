package es.urjc.si.domain.business.use_cases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.urjc.si.domain.dtos.ShoppingCartDto;
import es.urjc.si.domain.dtos.ShoppingCartInputDto;
import es.urjc.si.domain.exceptions.ShoppingCartNotFoundException;
import es.urjc.si.domain.ports.IShoppingCartRepository;
import es.urjc.si.domain.services.IShoppingCartService;

@DisplayName("Shopping Cart Use Case Domain Unit Test")
class ShoppingCartUseCaseTest {
	
	IShoppingCartRepository shoppingCartRepository = new ShoppingCartRepositoryStub();

	IShoppingCartService shoppingCartService;

	ShoppingCartInputDto dummyProduct;

	@BeforeEach
	void setUp() throws Exception {
		shoppingCartService = new ShoppingCartUseCase(shoppingCartRepository);

		dummyProduct = ShoppingCartInputDto.builder().customer("Dummy Customer").amount(0).finalized(false).build();
	}

	@Test
	@DisplayName("Create new shopping cart")
	void testSave() {
		ShoppingCartDto savedShoppingCart = shoppingCartService.save(dummyProduct);

		assertEquals(dummyProduct.getCustomer(), savedShoppingCart.getCustomer());
		assertEquals(dummyProduct.getAmount(), savedShoppingCart.getAmount(), 0.01);
		assertEquals(dummyProduct.isFinalized(), savedShoppingCart.isFinalized());

		assertEquals(savedShoppingCart, shoppingCartService.findById(savedShoppingCart.getId()));	}

	@Test
	@DisplayName("Delete existing shopping cart")
	void testDelete() {
		ShoppingCartDto savedShoppingCart = shoppingCartService.save(dummyProduct);
		
		ShoppingCartDto deletedShoppingCart = shoppingCartService.delete(savedShoppingCart.getId());

		assertThrows(ShoppingCartNotFoundException.class, () -> {
			shoppingCartService.findById(deletedShoppingCart.getId());
		});
	}

}
