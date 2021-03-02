package es.urjc.si.app.event.mappers;

import es.urjc.si.app.event.dtos.ShoppingCartCompletedEventDto;
import es.urjc.si.infra.db.h2.entities.CartExpenditure;

public class ShoppingCartExpenditureMapper {
	
	public static CartExpenditure map(ShoppingCartCompletedEventDto dto) {
		return CartExpenditure.builder().id(dto.getId()).expenditure(dto.getExpenditure())
				.build();
	}
}
