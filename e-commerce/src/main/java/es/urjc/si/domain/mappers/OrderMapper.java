package es.urjc.si.domain.mappers;

import java.util.ArrayList;
import java.util.List;

import es.urjc.si.domain.business.models.Order;
import es.urjc.si.domain.dtos.shoppingCart.OrderDto;

public class OrderMapper {

	public static Order map(OrderDto dto) {
		return Order.builder().id(dto.getId()).product(ProductMapper.map(dto.getProduct()))
				.quantity(dto.getQuantity()).build();
	}

	public static ArrayList<Order> map(List<OrderDto> dtos) {
		ArrayList<Order> orders = new ArrayList<>();

		dtos.forEach(dto -> orders.add(map(dto)));

		return orders;
	}
	
	public static OrderDto map(Order o) {
		return OrderDto.builder().id(o.getId()).product(ProductMapper.map(o.getProduct()))
				.quantity(o.getQuantity()).build();
	}

	public static ArrayList<OrderDto> map(ArrayList<Order> orders) {
		ArrayList<OrderDto> dtos = new ArrayList<>();

		orders.forEach(order -> dtos.add(map(order)));

		return dtos;
	}

}
