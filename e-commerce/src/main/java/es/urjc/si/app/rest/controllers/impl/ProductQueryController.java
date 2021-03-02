package es.urjc.si.app.rest.controllers.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.si.app.rest.controllers.IProductQueryController;
import es.urjc.si.app.rest.dtos.responses.ProductResponseDto;
import es.urjc.si.app.rest.mappers.ProductMapper;
import es.urjc.si.domain.ports.IProductRepository;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductQueryController implements IProductQueryController {
	
	private IProductRepository productRepository;

	@Override
	@GetMapping("/")
	public Collection<ProductResponseDto> getProducts() {
		return productRepository.findAll()
				.stream()
				.map(ProductMapper::map)
				.collect(Collectors.toList());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDto> getProduct(@PathVariable long id) {
		return ResponseEntity.ok(ProductMapper.map(productRepository.findById(id)));

	}

}
