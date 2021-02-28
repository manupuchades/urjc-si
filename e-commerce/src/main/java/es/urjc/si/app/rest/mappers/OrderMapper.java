package es.urjc.si.app.rest.mappers;

import java.util.ArrayList;
import java.util.Collection;

import es.urjc.si.app.rest.dtos.responses.OrderResponseDto;
import es.urjc.si.domain.dtos.OrderDto;
import es.urjc.si.domain.dtos.OrderInputDto;

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
	
	public static OrderInputDto map (long cart_id, long prod_id, int prod_quantity) {
		return OrderInputDto.builder().shoppingCartId(cart_id).productId(prod_id).quantity(prod_quantity).build();
	}

}
