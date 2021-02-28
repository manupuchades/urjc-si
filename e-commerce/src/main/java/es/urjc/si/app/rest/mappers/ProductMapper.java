package es.urjc.si.app.rest.mappers;

import es.urjc.si.app.rest.dtos.requests.ProductRequestDto;
import es.urjc.si.app.rest.dtos.responses.ProductResponseDto;
import es.urjc.si.domain.dtos.FullProductDto;
import es.urjc.si.domain.dtos.ProductInputDto;

public class ProductMapper {

	public static ProductResponseDto map(FullProductDto dto) {
		return ProductResponseDto.builder().id(dto.getId()).name(dto.getName()).description(dto.getDescription()).price(dto.getPrice())
				.build();
	}

	public static ProductInputDto map(ProductRequestDto p) {
		return ProductInputDto.builder().name(p.getName()).description(p.getDescription()).price(p.getPrice()).build();
	}

}
