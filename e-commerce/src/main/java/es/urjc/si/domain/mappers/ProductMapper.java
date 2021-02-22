package es.urjc.si.domain.mappers;

import es.urjc.si.domain.business.models.Product;
import es.urjc.si.domain.dtos.ProductDto;

public class ProductMapper {
	
	public static Product map(ProductDto dto) {
		return Product.builder().name(dto.getName()).description(dto.getDescription()).price(dto.getPrice()).build();
	}
	
	public static ProductDto map(Product p) {
		return ProductDto.builder().id(p.getId()).name(p.getName()).description(p.getDescription()).price(p.getPrice()).build();
	}

}
