package es.urjc.si.app.rest.controllers.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import es.urjc.si.app.rest.dtos.requests.ProductRequestDto;
import es.urjc.si.app.rest.dtos.responses.ProductResponseDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Product Controller REST End to End Test")
class ProductControllerE2ETest {

	@LocalServerPort
	int port;

	@Autowired
	private WebTestClient webTestClient;

	private static final String PRODUCTS_URI = "/api/products/";

	
	@DisplayName("Get all products")
	void testGetProducts() {
		this.webTestClient.get().uri(PRODUCTS_URI).exchange().expectStatus().isOk();
	}

	
	@DisplayName("Create new product")
	void testCreateProduct() {
		ProductRequestDto productDto = ProductRequestDto.builder().name("Risketos")
				.description("Aperitivo frito con sabor a queso").price(0.95).build();
		
		ProductResponseDto createdBook = this.webTestClient
				.post().uri(PRODUCTS_URI).bodyValue(productDto).exchange()
				.expectStatus().isCreated()
				.returnResult(ProductResponseDto.class).getResponseBody().single().block();
				
		this.webTestClient.get().uri(PRODUCTS_URI + createdBook.getId()).exchange()
			.expectStatus().isOk()
			.expectBody().jsonPath("$.name", productDto.getName());
	}

	
	@DisplayName("Delete product")
	void testDeleteProduct() {
		ProductRequestDto productDto = ProductRequestDto.builder().name("Fritos")
				.description("Snack de maiz con sabor a barbacoa").price(1.25).build();
		
		ProductResponseDto createdProduct = this.webTestClient
				.post()
				.uri(PRODUCTS_URI).bodyValue(productDto).exchange()
				.expectStatus().isCreated()
				.returnResult(ProductResponseDto.class).getResponseBody().single().block();
				
		this.webTestClient.get()
			.uri(PRODUCTS_URI + createdProduct.getId()).exchange()
			.expectStatus().isOk()
			.expectBody().jsonPath("$.name", productDto.getName());
		

        this.webTestClient.delete()
                .uri(PRODUCTS_URI + createdProduct.getId()).exchange()
                .expectStatus().isOk();

		this.webTestClient.get()
			.uri(PRODUCTS_URI + createdProduct.getId()).exchange()
			.expectStatus().isNotFound();
	}

}
