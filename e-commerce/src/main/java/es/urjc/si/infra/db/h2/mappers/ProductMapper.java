package es.urjc.si.infra.db.h2.mappers;

import es.urjc.si.domain.dtos.product.CreateProductCommandDto;
import es.urjc.si.domain.dtos.product.FullProductDto;
import es.urjc.si.infra.db.h2.entities.Product;

public class ProductMapper {
	
	public static Product map(CreateProductCommandDto dto) {
		return Product.builder().product_name(dto.getName()).product_description(dto.getDescription()).product_price(dto.getPrice()).build();
	}
	
	public static FullProductDto map(Product p) {
		return FullProductDto.builder().id(p.getId()).name(p.getProduct_name()).description(p.getProduct_description()).price(p.getProduct_price()).build();
	}

}
