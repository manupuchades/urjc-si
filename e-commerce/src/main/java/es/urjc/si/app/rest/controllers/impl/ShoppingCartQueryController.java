package es.urjc.si.app.rest.controllers.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.si.app.rest.controllers.IShoppingCartQueryController;
import es.urjc.si.app.rest.dtos.responses.ShoppingCartResponseDto;
import es.urjc.si.app.rest.mappers.ShoppingCartMapper;
import es.urjc.si.domain.ports.IShoppingCartRepository;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/shoppingcarts")
@AllArgsConstructor
public class ShoppingCartQueryController implements IShoppingCartQueryController {

	private IShoppingCartRepository shoppingCartrepository;

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<ShoppingCartResponseDto> getShoppingCart(@PathVariable long id) {
		return ResponseEntity.ok(ShoppingCartMapper.map(shoppingCartrepository.findById(id)));
	}

}
