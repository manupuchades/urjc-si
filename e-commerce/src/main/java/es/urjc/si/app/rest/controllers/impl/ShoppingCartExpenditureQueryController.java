package es.urjc.si.app.rest.controllers.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.si.app.rest.controllers.IShoppingCartExpenditureQueryController;
import es.urjc.si.app.rest.dtos.responses.CartExpenditureResponseDto;
import es.urjc.si.app.rest.mappers.ShoppingCartExpenditureMapper;
import es.urjc.si.domain.exceptions.ShoppingCartNotFoundException;
import es.urjc.si.infra.db.h2.repositories.CartExpenditureRepository;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/cartexpenditure")
@AllArgsConstructor
public class ShoppingCartExpenditureQueryController implements IShoppingCartExpenditureQueryController {

	CartExpenditureRepository cartExpenditure;
	
	@Override
	@GetMapping("/")
	public Collection<CartExpenditureResponseDto> getCartsExpenditure() {
		return cartExpenditure.findAll()
				.stream()
				.map(ShoppingCartExpenditureMapper::map)
				.collect(Collectors.toList());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<CartExpenditureResponseDto> getProduct(@PathVariable long id) {
		return ResponseEntity.ok(ShoppingCartExpenditureMapper.map(cartExpenditure.findById(id).orElseThrow(ShoppingCartNotFoundException::new)));
	}

}
