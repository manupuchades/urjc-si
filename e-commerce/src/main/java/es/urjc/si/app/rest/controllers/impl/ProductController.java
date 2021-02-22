package es.urjc.si.app.rest.controllers.impl;

import java.net.URI;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.urjc.si.app.rest.controllers.IProductController;
import es.urjc.si.app.rest.dtos.requests.ProductRequestDto;
import es.urjc.si.app.rest.dtos.responses.ProductResponseDto;
import es.urjc.si.app.rest.mappers.ProductMapper;
import es.urjc.si.domain.services.IProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@Tag(name = "Product", description = "the Product API")
@AllArgsConstructor
public class ProductController implements IProductController {
	
	private IProductService service;


	@Override
	public Collection<ProductResponseDto> getProducts() {
		return service.findAll()
				.stream()
				.map(ProductMapper::map)
				.collect(Collectors.toList());
	}

	@Override
	public ResponseEntity<ProductResponseDto> createProduct(@Valid ProductRequestDto product) {
		ProductResponseDto productResponse = ProductMapper.map(service.save(ProductMapper.map(product)));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(productResponse.getId()).toUri();
		return ResponseEntity.created(location).body(productResponse);
	}

	@Override
	public ResponseEntity<ProductResponseDto> getProduct(long id) {
		return ResponseEntity.ok(ProductMapper.map(service.findById(id)));

	}

	@Override
	public ResponseEntity<ProductResponseDto> deleteProduct(long id) {
		return ResponseEntity.ok(ProductMapper.map(service.delete(id)));
	}

}
