package es.urjc.si.app.ports;

import es.urjc.si.domain.dtos.product.CreateProductCommandDto;
import es.urjc.si.domain.dtos.product.DeleteProductCommandDto;

public interface IProductCommandPublisher {
	
    void createProduct(CreateProductCommandDto createProductDto);
    
    void deleteProduct(DeleteProductCommandDto deleteProductDto);
	
}
