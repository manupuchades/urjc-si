package es.urjc.si.domain.services;

import java.util.Collection;

import es.urjc.si.domain.dtos.FullProductDto;
import es.urjc.si.domain.dtos.ProductInputDto;

public interface IProductService {

	Collection<FullProductDto> findAll();
	
	FullProductDto findById(long id);
	
	FullProductDto save(ProductInputDto productDto);
	
	FullProductDto delete(long id);

}