package es.urjc.si.infra.db.h2.mappers;

import es.urjc.si.domain.dtos.shoppingCart.CreateShoppingCartCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.FullShoppingCartDto;
import es.urjc.si.infra.db.h2.entities.ShoppingCart;

public class ShoppingCartMapper {
	
	public static ShoppingCart map(CreateShoppingCartCommandDto dto) {
		return ShoppingCart.builder().customer(dto.getCustomer()).build();
	}
	
	public static FullShoppingCartDto map(ShoppingCart sc) {
		return FullShoppingCartDto.builder().id(sc.getId()).customer(sc.getCustomer()).finalized(sc.isFinalized()).orders(OrderMapper.map(sc.getProduct_orders())).build();
	}
}
