package es.urjc.si.domain.services;

import java.util.Collection;

import es.urjc.si.domain.dtos.ProductDto;
import es.urjc.si.domain.dtos.ProductInputDto;

public interface IProductService {

	Collection<ProductDto> findAll();
	
	ProductDto findById(long id);
	
	ProductDto save(ProductInputDto productDto);
	
	ProductDto delete(long id);

}