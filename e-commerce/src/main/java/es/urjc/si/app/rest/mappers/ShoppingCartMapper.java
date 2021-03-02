package es.urjc.si.app.rest.mappers;

import es.urjc.si.app.rest.dtos.requests.ProductRequestDto;
import es.urjc.si.app.rest.dtos.requests.ShoppingCartRequestDto;
import es.urjc.si.app.rest.dtos.responses.ShoppingCartResponseDto;
import es.urjc.si.domain.dtos.product.CreateProductCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.CreateShoppingCartCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.FullShoppingCartDto;

public class ShoppingCartMapper {
	
	public static ShoppingCartResponseDto map(FullShoppingCartDto dto) {
		return ShoppingCartResponseDto.builder().id(dto.getId()).customer(dto.getCustomer()).finalized(dto.isFinalized())
				.orders(OrderMapper.map(dto.getOrders())).build();
	}

	public static CreateProductCommandDto map(ProductRequestDto p) {
		return CreateProductCommandDto.builder().name(p.getName()).description(p.getDescription()).price(p.getPrice()).build();
	}

	public static CreateShoppingCartCommandDto map(ShoppingCartRequestDto shoppingCartRequestDto) {
		return CreateShoppingCartCommandDto.builder().customer(shoppingCartRequestDto.getCustomer()).build();
	}

}
