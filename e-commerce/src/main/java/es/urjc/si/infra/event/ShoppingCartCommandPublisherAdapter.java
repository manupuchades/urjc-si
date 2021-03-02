package es.urjc.si.infra.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import es.urjc.si.app.ports.IShoppingCartCommandPublisher;
import es.urjc.si.domain.dtos.shoppingCart.AddOrderCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.CreateShoppingCartCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.DeleteOrderCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.DeleteShoppingCartCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.FinalizeShoppingCartCommandDto;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ShoppingCartCommandPublisherAdapter implements IShoppingCartCommandPublisher {

	private final ApplicationEventPublisher applicationEventPublisher;

	@Override
	public void createShoppingCart(CreateShoppingCartCommandDto createShoppingCartDto) {
		applicationEventPublisher.publishEvent(createShoppingCartDto);
	}

	@Override
	public void deleteShoppingCart(DeleteShoppingCartCommandDto deleteShoppingCartDto) {
		applicationEventPublisher.publishEvent(deleteShoppingCartDto);
	}

	@Override
	public void finalizeShoppingCart(FinalizeShoppingCartCommandDto finalizeShoppingCartDto) {
		applicationEventPublisher.publishEvent(finalizeShoppingCartDto);
	}

	@Override
	public void addOrder(AddOrderCommandDto addOrderDto) {
		applicationEventPublisher.publishEvent(addOrderDto);
	}

	@Override
	public void deleteOrder(DeleteOrderCommandDto deleteOrderDto) {
		applicationEventPublisher.publishEvent(deleteOrderDto);
	}

}