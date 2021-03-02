package es.urjc.si.infra.event.mapper;

import es.urjc.si.domain.dtos.shoppingCart.ShoppingCartCompletedDto;
import es.urjc.si.infra.event.dtos.ShoppingCartCompletedEventDto;

public class ShoppingCartMapper {
	
	public static ShoppingCartCompletedEventDto map(ShoppingCartCompletedDto dto) {
		return ShoppingCartCompletedEventDto.builder().id(dto.getId()).expenditure(dto.getExpenditure()).build();
	}

}
