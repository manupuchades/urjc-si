package es.urjc.si.infra.db.h2.mappers;

import java.util.ArrayList;
import java.util.Collection;

import es.urjc.si.domain.dtos.shoppingCart.OrderDto;
import es.urjc.si.infra.db.h2.entities.Order;

public class OrderMapper {

	public static OrderDto map(Order o) {
		return OrderDto.builder().id(o.getId()).product(ProductMapper.map(o.getProduct()))
				.quantity(o.getOrder_quantity()).build();
	}

	public static ArrayList<OrderDto> map(Collection<Order> orders) {
		ArrayList<OrderDto> ordersDto = new ArrayList<>();

		orders.forEach(order -> ordersDto.add(map(order)));

		return ordersDto;
	}

}
