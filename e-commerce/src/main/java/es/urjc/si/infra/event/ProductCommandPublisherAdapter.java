package es.urjc.si.infra.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import es.urjc.si.app.ports.IProductCommandPublisher;
import es.urjc.si.domain.dtos.product.CreateProductCommandDto;
import es.urjc.si.domain.dtos.product.DeleteProductCommandDto;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProductCommandPublisherAdapter implements IProductCommandPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;
	
	@Override
	public void createProduct(CreateProductCommandDto createProductDto) {
		applicationEventPublisher.publishEvent(createProductDto);	
	}

	@Override
	public void deleteProduct(DeleteProductCommandDto deleteProductDto) {
		applicationEventPublisher.publishEvent(deleteProductDto);
	}

}
