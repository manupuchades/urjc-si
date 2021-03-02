package es.urjc.si.infra.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import es.urjc.si.domain.dtos.shoppingCart.ShoppingCartCompletedDto;
import es.urjc.si.domain.ports.IShoppingCartPublisher;
import es.urjc.si.infra.event.mapper.ShoppingCartMapper;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ShoppingCartPublisherAdapter implements IShoppingCartPublisher{
	
    private final ApplicationEventPublisher applicationEventPublisher;

	@Override
	public void publishCartCompleted(ShoppingCartCompletedDto shoppingCart) {
		applicationEventPublisher.publishEvent(ShoppingCartMapper.map(shoppingCart));
	}

}
