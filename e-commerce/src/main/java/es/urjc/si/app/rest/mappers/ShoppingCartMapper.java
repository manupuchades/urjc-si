package es.urjc.si.app.rest.mappers;

import es.urjc.si.app.rest.dtos.requests.ProductRequestDto;
import es.urjc.si.app.rest.dtos.requests.ShoppingCartRequestDto;
import es.urjc.si.app.rest.dtos.responses.ShoppingCartResponseDto;
import es.urjc.si.domain.dtos.FullShoppingCartDto;
import es.urjc.si.domain.dtos.ProductInputDto;
import es.urjc.si.domain.dtos.ShoppingCartInputDto;

public class ShoppingCartMapper {
	
	public static ShoppingCartResponseDto map(FullShoppingCartDto dto) {
		return ShoppingCartResponseDto.builder().id(dto.getId()).customer(dto.getCustomer()).finalized(dto.isFinalized())
				.orders(OrderMapper.map(dto.getOrders())).build();
	}

	public static ProductInputDto map(ProductRequestDto p) {
		return ProductInputDto.builder().name(p.getName()).description(p.getDescription()).price(p.getPrice()).build();
	}

	public static ShoppingCartInputDto map(ShoppingCartRequestDto shoppingCartRequestDto) {
		return ShoppingCartInputDto.builder().customer(shoppingCartRequestDto.getCustomer()).build();
	}

}
