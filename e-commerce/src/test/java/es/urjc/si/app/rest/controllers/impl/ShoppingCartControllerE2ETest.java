package es.urjc.si.app.rest.controllers.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import es.urjc.si.app.rest.dtos.requests.ProductRequestDto;
import es.urjc.si.app.rest.dtos.requests.ShoppingCartRequestDto;
import es.urjc.si.app.rest.dtos.responses.ProductResponseDto;
import es.urjc.si.app.rest.dtos.responses.ShoppingCartResponseDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Shopping Cart Controller REST End to End Test")
class ShoppingCartControllerE2ETest {

	@LocalServerPort
	int port;

	@Autowired
	private WebTestClient webTestClient;

	private static final String SHOPPING_CARTS_URI = "/api/shoppingcarts/";
	
	private static final String PRODUCTS_URI = "/api/products/";

	@Test
	@DisplayName("Create new shopping cart")
	void testCreateShoppingCart() {
		ShoppingCartRequestDto shoppingCartDto = ShoppingCartRequestDto.builder().customer("Create Cart Test").build();
		
		ShoppingCartResponseDto createdShoppingCart = this.webTestClient
				.post().uri(SHOPPING_CARTS_URI).bodyValue(shoppingCartDto).exchange()
				.expectStatus().isCreated()
				.returnResult(ShoppingCartResponseDto.class).getResponseBody().single().block();
				
		this.webTestClient.get().uri(SHOPPING_CARTS_URI + createdShoppingCart.getId()).exchange()
			.expectStatus().isOk()
			.expectBody().jsonPath("$.customer", shoppingCartDto.getCustomer());
	}

	@Test
	@DisplayName("Delete existing shopping cart")
	void testDeleteShoppingCart() {
		ShoppingCartRequestDto shoppingCartDto = ShoppingCartRequestDto.builder().customer("Delete Cart Test").build();
		
		ShoppingCartResponseDto createdShoppingCart = this.webTestClient
				.post().uri(SHOPPING_CARTS_URI).bodyValue(shoppingCartDto).exchange()
				.expectStatus().isCreated()
				.returnResult(ShoppingCartResponseDto.class).getResponseBody().single().block();
				
		this.webTestClient.get().uri(SHOPPING_CARTS_URI + createdShoppingCart.getId()).exchange()
			.expectStatus().isOk()
			.expectBody().jsonPath("$.customer", shoppingCartDto.getCustomer());
		

        this.webTestClient.delete()
                .uri(SHOPPING_CARTS_URI + createdShoppingCart.getId()).exchange()
                .expectStatus().isOk();

		this.webTestClient.get()
			.uri(SHOPPING_CARTS_URI + createdShoppingCart.getId()).exchange()
			.expectStatus().isNotFound();
	}

	@Test
	@DisplayName("Add product to existing shopping cart")
	void testAddProductToShoppingCart() {
		
		ProductRequestDto productDto = ProductRequestDto.builder().name("Fritos")
				.description("Snack de maiz con sabor a barbacoa").price(1.25).build();
		
		ProductResponseDto createdProduct = this.webTestClient
				.post()
				.uri(PRODUCTS_URI).bodyValue(productDto).exchange()
				.expectStatus().isCreated()
				.returnResult(ProductResponseDto.class).getResponseBody().single().block();
		
		ShoppingCartRequestDto shoppingCartDto = ShoppingCartRequestDto.builder().customer("Add Product to Cart Test").build();
		
		ShoppingCartResponseDto createdShoppingCart = this.webTestClient
				.post().uri(SHOPPING_CARTS_URI).bodyValue(shoppingCartDto).exchange()
				.expectStatus().isCreated()
				.returnResult(ShoppingCartResponseDto.class).getResponseBody().single().block();
		
		ShoppingCartResponseDto updatedShoppingCart = this.webTestClient
				.post().uri(SHOPPING_CARTS_URI + createdShoppingCart.getId() + "/product/" + createdProduct.getId() + "/quantity/" + 3).exchange()
				.expectStatus().isOk()
				.returnResult(ShoppingCartResponseDto.class).getResponseBody().single().block();
		
		assertEquals(1, updatedShoppingCart.getOrders().size());
		assertEquals(3, updatedShoppingCart.getOrders().get(0).getQuantity());
		
		assertEquals(productDto.getName(), updatedShoppingCart.getOrders().get(0).getProduct().getName());
		assertEquals(productDto.getDescription(), updatedShoppingCart.getOrders().get(0).getProduct().getDescription());
		assertEquals(productDto.getPrice(), updatedShoppingCart.getOrders().get(0).getProduct().getPrice(), 0.01);

	}

	@Test
	@DisplayName("Delete order from cart")
	void testDeleteProductFromShoppingCart() {
		ProductRequestDto productDto = ProductRequestDto.builder().name("Fritos")
				.description("Snack de maiz con sabor a barbacoa").price(1.25).build();
		
		ProductResponseDto createdProduct = this.webTestClient
				.post()
				.uri(PRODUCTS_URI).bodyValue(productDto).exchange()
				.expectStatus().isCreated()
				.returnResult(ProductResponseDto.class).getResponseBody().single().block();
		
		ShoppingCartRequestDto shoppingCartDto = ShoppingCartRequestDto.builder().customer("Add Product to Cart Test").build();
		
		ShoppingCartResponseDto createdShoppingCart = this.webTestClient
				.post().uri(SHOPPING_CARTS_URI).bodyValue(shoppingCartDto).exchange()
				.expectStatus().isCreated()
				.returnResult(ShoppingCartResponseDto.class).getResponseBody().single().block();
		
		ShoppingCartResponseDto updatedShoppingCart = this.webTestClient
				.post().uri(SHOPPING_CARTS_URI + createdShoppingCart.getId() + "/product/" + createdProduct.getId() + "/quantity/" + 3).exchange()
				.expectStatus().isOk()
				.returnResult(ShoppingCartResponseDto.class).getResponseBody().single().block();
		
		assertEquals(1, updatedShoppingCart.getOrders().size());
		assertEquals(3, updatedShoppingCart.getOrders().get(0).getQuantity());
		
		assertEquals(productDto.getName(), updatedShoppingCart.getOrders().get(0).getProduct().getName());
		assertEquals(productDto.getDescription(), updatedShoppingCart.getOrders().get(0).getProduct().getDescription());
		assertEquals(productDto.getPrice(), updatedShoppingCart.getOrders().get(0).getProduct().getPrice(), 0.01);
		
		ShoppingCartResponseDto deletedShoppingCart = this.webTestClient
				.delete().uri(SHOPPING_CARTS_URI + createdShoppingCart.getId() + "/product/" + createdProduct.getId()).exchange()
				.expectStatus().isOk()
				.returnResult(ShoppingCartResponseDto.class).getResponseBody().single().block();
		
		assertEquals(0, deletedShoppingCart.getOrders().size());

	}

}
