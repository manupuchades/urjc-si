package es.urjc.si.app.rest.controllers.impl;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.si.app.ports.IProductCommandPublisher;
import es.urjc.si.app.rest.controllers.IProductCommandController;
import es.urjc.si.app.rest.dtos.requests.ProductRequestDto;
import es.urjc.si.app.rest.mappers.ProductMapper;
import es.urjc.si.domain.dtos.product.DeleteProductCommandDto;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductCommandController implements IProductCommandController {
	
	private IProductCommandPublisher productPublisher;

	@Override
	@PostMapping("/")
	public ResponseEntity<String> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
		productPublisher.createProduct(ProductMapper.map(productRequestDto));

		return ResponseEntity.ok("Request received");
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable long id) {
		productPublisher.deleteProduct(new DeleteProductCommandDto(id));
		
		return ResponseEntity.ok("Request received");
	}

}
