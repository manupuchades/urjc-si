package es.urjc.si.infra.db.h2;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import es.urjc.si.domain.dtos.FullProductDto;
import es.urjc.si.domain.dtos.ProductInputDto;
import es.urjc.si.domain.exceptions.ProductNotFoundException;
import es.urjc.si.domain.ports.IProductRepository;
import es.urjc.si.infra.db.h2.mappers.ProductMapper;
import es.urjc.si.infra.db.h2.repositories.ProductRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProductAdapter implements IProductRepository {
	
	private ProductRepository productRepository;

	@Override
	public Collection<FullProductDto> findAll() {
				
		return productRepository.findAll()
				.stream()
				.map(ProductMapper::map)
				.collect(Collectors.toList());
	}

	@Override
	public FullProductDto findById(long id) {
		return productRepository.findById(id).map(ProductMapper::map).orElseThrow(ProductNotFoundException::new);
	}

	@Override
	public FullProductDto save(ProductInputDto product) {
		return ProductMapper.map(productRepository.save(ProductMapper.map(product)));
	}

	@Override
	public FullProductDto delete(long id) {
		FullProductDto p = findById(id);
		productRepository.deleteById(id);
		return p;
	}

}
