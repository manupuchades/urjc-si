package es.urjc.si.domain.mappers;

import es.urjc.si.domain.business.models.Product;
import es.urjc.si.domain.dtos.FullProductDto;

public class ProductMapper {
	
	public static Product map(FullProductDto dto) {
		return Product.builder().name(dto.getName()).description(dto.getDescription()).price(dto.getPrice()).build();
	}
	
	public static FullProductDto map(Product p) {
		return FullProductDto.builder().id(p.getId()).name(p.getName()).description(p.getDescription()).price(p.getPrice()).build();
	}

}
