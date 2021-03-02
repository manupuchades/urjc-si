package es.urjc.si.app.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import es.urjc.si.domain.dtos.product.CreateProductCommandDto;
import es.urjc.si.domain.dtos.product.DeleteProductCommandDto;
import es.urjc.si.domain.services.IProductService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ProductCommandEventListener {
	
	private IProductService productService;
	
    @EventListener
    public void createProduct(CreateProductCommandDto createProductDto) {
    	productService.save(createProductDto);
    }
    
    @EventListener
    public void deleteProduct(DeleteProductCommandDto deleteProductDto) {
    	productService.delete(deleteProductDto.getId());
    }
}
