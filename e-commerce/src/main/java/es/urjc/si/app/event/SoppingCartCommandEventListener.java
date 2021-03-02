package es.urjc.si.app.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import es.urjc.si.domain.dtos.shoppingCart.AddOrderCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.CreateShoppingCartCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.DeleteOrderCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.DeleteShoppingCartCommandDto;
import es.urjc.si.domain.dtos.shoppingCart.FinalizeShoppingCartCommandDto;
import es.urjc.si.domain.services.IShoppingCartService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class SoppingCartCommandEventListener {
	
	private IShoppingCartService shoppingCartService;
	
	@EventListener
    public void createShoppingCart(CreateShoppingCartCommandDto createShoppingCartDto) {
		shoppingCartService.save(createShoppingCartDto);
	}

	@EventListener
    public void deleteShoppingCart(DeleteShoppingCartCommandDto deleteShoppingCartDto) {
		shoppingCartService.delete(deleteShoppingCartDto.getId());
	}

	@EventListener
    public void finalizeShoppingCart(FinalizeShoppingCartCommandDto finalizeShoppingCartDto) {
		shoppingCartService.finalize(finalizeShoppingCartDto.getId());
	}

	@EventListener
    public void addOrder(AddOrderCommandDto addOrderDto) {
		shoppingCartService.addOrder(addOrderDto);
	}

	@EventListener
    public void deleteOrder(DeleteOrderCommandDto deleteOrderDto) {
		shoppingCartService.deleteOrder(deleteOrderDto);
	}

}
