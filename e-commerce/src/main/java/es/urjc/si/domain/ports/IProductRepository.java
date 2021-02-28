package es.urjc.si.domain.ports;

import java.util.Collection;

import es.urjc.si.domain.dtos.FullProductDto;
import es.urjc.si.domain.dtos.ProductInputDto;

public interface IProductRepository {

	Collection<FullProductDto> findAll();
	
	FullProductDto findById(long id);
	
	FullProductDto save(ProductInputDto product);
	
	FullProductDto delete(long id);

}