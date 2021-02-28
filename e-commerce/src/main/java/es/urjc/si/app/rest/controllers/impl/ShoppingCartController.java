package es.urjc.si.app.rest.controllers.impl;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.urjc.si.app.rest.controllers.IShoppingCartController;
import es.urjc.si.app.rest.dtos.requests.ShoppingCartRequestDto;
import es.urjc.si.app.rest.dtos.responses.ShoppingCartResponseDto;
import es.urjc.si.app.rest.mappers.OrderMapper;
import es.urjc.si.app.rest.mappers.ShoppingCartMapper;
import es.urjc.si.domain.services.IShoppingCartService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/shoppingcarts")
@AllArgsConstructor
public class ShoppingCartController implements IShoppingCartController {

	private IShoppingCartService shoppingCartService;
	
	@Override
	@PostMapping("/")
	public ResponseEntity<ShoppingCartResponseDto> createShoppingCart(@Valid @RequestBody ShoppingCartRequestDto shoppingCartRequestDto) {
		ShoppingCartResponseDto shoppingCartResponseDto = ShoppingCartMapper.map(shoppingCartService.save(ShoppingCartMapper.map(shoppingCartRequestDto)));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(shoppingCartResponseDto.getId()).toUri();
		return ResponseEntity.created(location).body(shoppingCartResponseDto);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<ShoppingCartResponseDto> getShoppingCart(@PathVariable long id) {
		return ResponseEntity.ok(ShoppingCartMapper.map(shoppingCartService.findById(id)));
	}
	
	@Override
	@PatchMapping("/{id}")
	public ResponseEntity<ShoppingCartResponseDto> finalizeShoppingCart(@PathVariable long id) {
		return ResponseEntity.ok(ShoppingCartMapper.map(shoppingCartService.finalize(id)));
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<ShoppingCartResponseDto> deleteShoppingCart(@PathVariable long id) {
		return ResponseEntity.ok(ShoppingCartMapper.map(shoppingCartService.delete(id)));
	}

	@Override
	@PostMapping("/{cart_id}/product/{prod_id}/quantity/{prod_quantity}")
	public ResponseEntity<ShoppingCartResponseDto> addProductToShoppingCart(
			@PathVariable long cart_id, 
			@PathVariable long prod_id,
			@PathVariable int prod_quantity) {
		return ResponseEntity.ok(ShoppingCartMapper.map(shoppingCartService.addOrder(OrderMapper.map(cart_id, prod_id, prod_quantity))));
	}

	@Override
	@DeleteMapping("/{cart_id}/product/{prod_id}")
	public ResponseEntity<ShoppingCartResponseDto> deleteProductFromShoppingCart(@PathVariable long cart_id, @PathVariable long prod_id) {
		return ResponseEntity.ok(ShoppingCartMapper.map(shoppingCartService.deleteOrder(OrderMapper.map(cart_id, prod_id, 0))));
	}

}
