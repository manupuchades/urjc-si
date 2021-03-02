package es.urjc.si.domain.ports;

import java.util.Collection;

import es.urjc.si.domain.dtos.product.CreateProductCommandDto;
import es.urjc.si.domain.dtos.product.FullProductDto;

public interface IProductRepository {

	Collection<FullProductDto> findAll();
	
	FullProductDto findById(long id);
	
	FullProductDto save(CreateProductCommandDto product);
	
	FullProductDto delete(long id);

}