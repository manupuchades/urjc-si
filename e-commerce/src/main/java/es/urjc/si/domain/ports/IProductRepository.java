package es.urjc.si.domain.ports;

import java.util.Collection;

import es.urjc.si.domain.dtos.ProductDto;
import es.urjc.si.domain.dtos.ProductInputDto;

public interface IProductRepository {

	Collection<ProductDto> findAll();
	
	ProductDto findById(long id);
	
	ProductDto save(ProductInputDto product);
	
	ProductDto delete(long id);

}