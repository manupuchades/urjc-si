package es.urjc.si.app.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import es.urjc.si.app.event.dtos.ShoppingCartCompletedEventDto;
import es.urjc.si.app.event.mappers.ShoppingCartExpenditureMapper;
import es.urjc.si.infra.db.h2.repositories.CartExpenditureRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ShoppingCartFinalizedEventListener {

	CartExpenditureRepository cartExpenditure;
	
    @EventListener
    public void shoppingCartCompleted(ShoppingCartCompletedEventDto shoppingCartCompletedEvent) {
    	cartExpenditure.save(ShoppingCartExpenditureMapper.map(shoppingCartCompletedEvent));
    }
}
