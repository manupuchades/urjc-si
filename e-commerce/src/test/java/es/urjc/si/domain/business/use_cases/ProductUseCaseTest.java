package es.urjc.si.domain.business.use_cases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.urjc.si.domain.business.use_cases.stubs.ProductRepositoryStub;
import es.urjc.si.domain.dtos.FullProductDto;
import es.urjc.si.domain.dtos.ProductInputDto;
import es.urjc.si.domain.exceptions.ProductNotFoundException;
import es.urjc.si.domain.ports.IProductRepository;
import es.urjc.si.domain.services.IProductService;

@DisplayName("Product Use Case Domain Unit Test")
class ProductUseCaseTest {

	IProductRepository productRepository = new ProductRepositoryStub();

	IProductService productService;

	ProductInputDto dummyProduct;

	@BeforeEach
	void setUp() {
		productService = new ProductUseCase(productRepository);

		dummyProduct = ProductInputDto.builder().name("Pipas Facundo").description("Pipas de Girasol blanquilla")
				.price(Double.valueOf("1.91")).build();
	}

	@Test
	@DisplayName("Create new product")
	void saveProductTest() {
		FullProductDto savedProduct = productService.save(dummyProduct);

		assertEquals(dummyProduct.getName(), savedProduct.getName());
		assertEquals(dummyProduct.getDescription(), savedProduct.getDescription());
		assertEquals(dummyProduct.getPrice(), savedProduct.getPrice(), 0.01);
		assertEquals(savedProduct, productService.findById(savedProduct.getId()));
	}

	@Test
	@DisplayName("Delete existing product")
	void deleteProductTest() {
		FullProductDto savedProduct = productService.save(dummyProduct);
		assertEquals(savedProduct, productService.findById(savedProduct.getId()));

		FullProductDto deletedProduct = productService.delete(savedProduct.getId());

		assertEquals(savedProduct, deletedProduct);
		assertThrows(ProductNotFoundException.class, () -> {
			productService.findById(savedProduct.getId());
		});
	}

}
