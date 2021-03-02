package es.urjc.si.app.rest.controllers.impl;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.si.app.ports.IShoppingCartCommandPublisher;
import es.urjc.si.app.rest.controllers.IShoppingCartCommandController;
import es.urjc.si.app.rest.dtos.requests.ShoppingCartRequestDto;
import es.urjc.si.app.rest.mappers.OrderMapper;
import es.urjc.si.app.rest.mappers.ShoppingCartMapper;
import es.urjc.si.domain.dtos.shoppingCart.DeleteShoppingCartCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.FinalizeShoppingCartCommandDto;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/shoppingcarts")
@AllArgsConstructor
public class ShoppingCartCommandController implements IShoppingCartCommandController {

	private IShoppingCartCommandPublisher shoppingCartPublisher;

	@Override
	@PostMapping("/")
	public ResponseEntity<String> createShoppingCart(
			@Valid @RequestBody ShoppingCartRequestDto shoppingCartRequestDto) {
		shoppingCartPublisher.createShoppingCart(ShoppingCartMapper.map(shoppingCartRequestDto));
		return ResponseEntity.ok("Request received");
	}

	@Override
	@PatchMapping("/{id}")
	public ResponseEntity<String> finalizeShoppingCart(@PathVariable long id) {
		shoppingCartPublisher.finalizeShoppingCart(new FinalizeShoppingCartCommandDto(id));
		return ResponseEntity.ok("Request received");
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteShoppingCart(@PathVariable long id) {
		shoppingCartPublisher.deleteShoppingCart(new DeleteShoppingCartCommandDto(id));
		return ResponseEntity.ok("Request received");
	}

	@Override
	@PostMapping("/{cart_id}/product/{prod_id}/quantity/{prod_quantity}")
	public ResponseEntity<String> addProductToShoppingCart(
			@PathVariable long cart_id, 
			@PathVariable long prod_id,
			@PathVariable int prod_quantity) {
		shoppingCartPublisher.addOrder(OrderMapper.map(cart_id, prod_id, prod_quantity));
		return ResponseEntity.ok("Request received");
	}

	@Override
	@DeleteMapping("/{cart_id}/product/{prod_id}")
	public ResponseEntity<String> deleteProductFromShoppingCart(
			@PathVariable long cart_id,
			@PathVariable long prod_id) {
		shoppingCartPublisher.deleteOrder(OrderMapper.map(cart_id, prod_id));
		return ResponseEntity.ok("Request received");
	}
}
