package es.urjc.si.app.rest.controllers.impl;

import java.net.URI;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.urjc.si.app.rest.controllers.IProductController;
import es.urjc.si.app.rest.dtos.requests.ProductRequestDto;
import es.urjc.si.app.rest.dtos.responses.ProductResponseDto;
import es.urjc.si.app.rest.mappers.ProductMapper;
import es.urjc.si.domain.services.IProductService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController implements IProductController {
	
	private IProductService productService;

	@Override
	@GetMapping("/")
	public Collection<ProductResponseDto> getProducts() {
		return productService.findAll()
				.stream()
				.map(ProductMapper::map)
				.collect(Collectors.toList());
	}

	@Override
	@PostMapping("/")
	public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
		ProductResponseDto productResponseDto = ProductMapper.map(productService.save(ProductMapper.map(productRequestDto)));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(productResponseDto.getId()).toUri();
		return ResponseEntity.created(location).body(productResponseDto);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDto> getProduct(@PathVariable long id) {
		return ResponseEntity.ok(ProductMapper.map(productService.findById(id)));

	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable long id) {
		return ResponseEntity.ok(ProductMapper.map(productService.delete(id)));
	}

}
