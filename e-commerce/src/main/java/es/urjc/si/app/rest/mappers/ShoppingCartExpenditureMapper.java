package es.urjc.si.app.rest.mappers;

import es.urjc.si.app.rest.dtos.responses.CartExpenditureResponseDto;
import es.urjc.si.infra.db.h2.entities.CartExpenditure;

public class ShoppingCartExpenditureMapper {

	public static CartExpenditureResponseDto map(CartExpenditure ce) {
		return CartExpenditureResponseDto.builder().cartId(ce.getId()).expenditure(ce.getExpenditure())
				.build();
	}
	
}
