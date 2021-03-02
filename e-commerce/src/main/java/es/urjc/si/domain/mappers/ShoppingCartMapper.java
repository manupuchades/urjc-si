package es.urjc.si.domain.mappers;

import es.urjc.si.domain.business.models.ShoppingCart;
import es.urjc.si.domain.dtos.shoppingCart.FullShoppingCartDto;

public class ShoppingCartMapper {
	
	public static ShoppingCart map(FullShoppingCartDto dto) {
		return ShoppingCart.builder().id(dto.getId()).customer(dto.getCustomer()).finalized(dto.isFinalized()).orders(OrderMapper.map(dto.getOrders())).build();
	}
	
	public static FullShoppingCartDto map(ShoppingCart sc) {
		return FullShoppingCartDto.builder().id(sc.getId()).customer(sc.getCustomer()).finalized(sc.isFinalized()).orders(OrderMapper.map(sc.getOrders())).build();
	}
	
}
