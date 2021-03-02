package es.urjc.si.domain.business.use_cases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.urjc.si.domain.business.use_cases.stubs.ProductRepositoryStub;
import es.urjc.si.domain.business.use_cases.stubs.ShoppingCartPublisherStub;
import es.urjc.si.domain.business.use_cases.stubs.ShoppingCartRepositoryStub;
import es.urjc.si.domain.business.use_cases.stubs.ShoppingCartValidatorStub;
import es.urjc.si.domain.dtos.product.CreateProductCommandDto;
import es.urjc.si.domain.dtos.product.FullProductDto;
import es.urjc.si.domain.dtos.shoppingCart.AddOrderCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.CreateShoppingCartCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.DeleteOrderCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.FullShoppingCartDto;
import es.urjc.si.domain.exceptions.ShoppingCartNotFoundException;
import es.urjc.si.domain.ports.IProductRepository;
import es.urjc.si.domain.ports.IShoppingCartPublisher;
import es.urjc.si.domain.ports.IShoppingCartRepository;
import es.urjc.si.domain.ports.IShoppingCartValidator;
import es.urjc.si.domain.services.IShoppingCartService;

@DisplayName("Shopping Cart Use Case Domain Unit Test")
class ShoppingCartUseCaseTest {

	IProductRepository productRepository = new ProductRepositoryStub();
	IShoppingCartRepository shoppingCartRepository = new ShoppingCartRepositoryStub(productRepository);
	IShoppingCartValidator shoppingCartValidator = new ShoppingCartValidatorStub();
	IShoppingCartPublisher shoppingCartPublisher = new ShoppingCartPublisherStub();

	IShoppingCartService shoppingCartService;

	CreateShoppingCartCommandDto dummyShoppingCart;
	CreateProductCommandDto dummyProduct;

	@BeforeEach
	void setUp() throws Exception {
		shoppingCartService = new ShoppingCartUseCase(shoppingCartRepository, productRepository, shoppingCartValidator, shoppingCartPublisher);

		dummyShoppingCart = CreateShoppingCartCommandDto.builder().customer("Dummy Customer").build();
		dummyProduct = CreateProductCommandDto.builder().name("Doritos Tex Mex")
				.description("Nachos de maiz con sabor a queso de Matutano").price(Double.valueOf("2.55")).build();

		productRepository.save(dummyProduct);
	}

	@Test
	@DisplayName("Create new shopping cart")
	void testSave() {
		FullShoppingCartDto savedShoppingCart = shoppingCartService.save(dummyShoppingCart);

		assertEquals(dummyShoppingCart.getCustomer(), savedShoppingCart.getCustomer());
		assertFalse(savedShoppingCart.isFinalized());

		assertEquals(savedShoppingCart, shoppingCartService.findById(savedShoppingCart.getId()));
	}

	@Test
	@DisplayName("Delete existing shopping cart")
	void testDelete() {
		FullShoppingCartDto savedShoppingCart = shoppingCartService.save(dummyShoppingCart);

		FullShoppingCartDto deletedShoppingCart = shoppingCartService.delete(savedShoppingCart.getId());

		assertThrows(ShoppingCartNotFoundException.class, () -> {
			shoppingCartService.findById(deletedShoppingCart.getId());
		});
	}

	@Test
	@DisplayName("Add product to existing shopping cart")
	void testAddProductToShoppingCart() {
		FullShoppingCartDto savedShoppingCart = shoppingCartRepository.save(dummyShoppingCart);
		FullProductDto savedProduct = productRepository.save(CreateProductCommandDto.builder().name("Aceitunas la española")
				.description("Una aceituna como ninguna").price(Double.valueOf("0.94")).build());

		int quantity = 2;

		AddOrderCommandDto order = AddOrderCommandDto.builder().shoppingCartId(savedShoppingCart.getId())
				.productId(savedProduct.getId()).quantity(quantity).build();

		FullShoppingCartDto updatedShoppingCart = shoppingCartService.addOrder(order);

		assertEquals(dummyShoppingCart.getCustomer(), updatedShoppingCart.getCustomer());
		assertFalse(updatedShoppingCart.isFinalized());
		assertEquals(1, updatedShoppingCart.getOrders().size());

		assertEquals(savedProduct.getName(), updatedShoppingCart.getOrders().get(0).getProduct().getName());
		assertEquals(savedProduct.getDescription(),
				updatedShoppingCart.getOrders().get(0).getProduct().getDescription());
		assertEquals(quantity, updatedShoppingCart.getOrders().get(0).getQuantity());

	}

	@Test
	@DisplayName("Delete order from cart")
	void testDeleteProductFromShoppingCart() {
		FullShoppingCartDto savedShoppingCart = shoppingCartRepository.save(dummyShoppingCart);
		FullProductDto savedProduct = productRepository.save(CreateProductCommandDto.builder().name("Doritos Tex Mex")
				.description("Nachos de maiz con sabor a queso de Matutano").price(Double.valueOf("2.55")).build());

		int quantity = 4;

		AddOrderCommandDto order = AddOrderCommandDto.builder().shoppingCartId(savedShoppingCart.getId())
				.productId(savedProduct.getId()).quantity(quantity).build();

		FullShoppingCartDto updatedShoppingCart = shoppingCartService.addOrder(order);

		assertEquals(dummyShoppingCart.getCustomer(), updatedShoppingCart.getCustomer());
		assertFalse(updatedShoppingCart.isFinalized());
		assertEquals(1, updatedShoppingCart.getOrders().size());

		assertEquals(savedProduct.getName(), updatedShoppingCart.getOrders().get(0).getProduct().getName());
		assertEquals(savedProduct.getDescription(),updatedShoppingCart.getOrders().get(0).getProduct().getDescription());
		assertEquals(quantity, updatedShoppingCart.getOrders().get(0).getQuantity());
		
		DeleteOrderCommandDto deleteOrder = DeleteOrderCommandDto.builder().productId(order.getProductId()).shoppingCartId(order.getShoppingCartId()).build();
		
		FullShoppingCartDto deletedProductShoppingCart = shoppingCartService.deleteOrder(deleteOrder);
		
		assertEquals(dummyShoppingCart.getCustomer(), deletedProductShoppingCart.getCustomer());
		assertFalse(deletedProductShoppingCart.isFinalized());
		assertEquals(0, deletedProductShoppingCart.getOrders().size());

	}

}
