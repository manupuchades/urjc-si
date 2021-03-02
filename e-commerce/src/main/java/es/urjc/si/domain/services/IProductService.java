package es.urjc.si.domain.services;

import java.util.Collection;

import es.urjc.si.domain.dtos.product.CreateProductCommandDto;
import es.urjc.si.domain.dtos.product.FullProductDto;

public interface IProductService {

	Collection<FullProductDto> findAll();
	
	FullProductDto findById(long id);
	
	FullProductDto save(CreateProductCommandDto productDto);
	
	FullProductDto delete(long id);

}