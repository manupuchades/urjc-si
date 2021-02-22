package es.urjc.si.infra.db.h2.mappers;

import es.urjc.si.domain.dtos.ProductDto;
import es.urjc.si.domain.dtos.ProductInputDto;
import es.urjc.si.infra.db.h2.entities.Product;

public class ProductMapper {
	
	public static Product map(ProductInputDto dto) {
		return Product.builder().product_name(dto.getName()).product_description(dto.getDescription()).product_price(dto.getPrice()).build();
	}
	
	public static ProductDto map(Product p) {
		return ProductDto.builder().id(p.getId()).name(p.getProduct_name()).description(p.getProduct_description()).price(p.getProduct_price()).build();
	}

}
