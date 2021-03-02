package es.urjc.si.app.rest.mappers;

import java.util.ArrayList;
import java.util.Collection;

import es.urjc.si.app.rest.dtos.responses.OrderResponseDto;
import es.urjc.si.domain.dtos.shoppingCart.AddOrderCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.DeleteOrderCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.OrderDto;

public class OrderMapper {

	public static OrderResponseDto map(OrderDto o) {
		return OrderResponseDto.builder().id(o.getId()).product(ProductMapper.map(o.getProduct()))
				.quantity(o.getQuantity()).build();
	}

	public static ArrayList<OrderResponseDto> map(Collection<OrderDto> orders) {
		ArrayList<OrderResponseDto> ordersResponseDto = new ArrayList<>();
		orders.forEach(order -> ordersResponseDto.add(map(order)));
		return ordersResponseDto;
	}
	
	public static AddOrderCommandDto map (long cart_id, long prod_id, int prod_quantity) {
		return AddOrderCommandDto.builder().shoppingCartId(cart_id).productId(prod_id).quantity(prod_quantity).build();
	}
	
	public static DeleteOrderCommandDto map (long cart_id, long prod_id) {
		return DeleteOrderCommandDto.builder().shoppingCartId(cart_id).productId(prod_id).build();
	}

}
