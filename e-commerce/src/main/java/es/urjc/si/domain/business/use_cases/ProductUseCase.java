package es.urjc.si.domain.business.use_cases;

import java.util.Collection;

import es.urjc.si.domain.dtos.ProductDto;
import es.urjc.si.domain.dtos.ProductInputDto;
import es.urjc.si.domain.ports.IProductRepository;
import es.urjc.si.domain.services.IProductService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductUseCase implements IProductService {

	IProductRepository productRepository;

	@Override
	public Collection<ProductDto> findAll() {
		return productRepository.findAll();
	}

	@Override
	public ProductDto findById(long id) {
		return productRepository.findById(id);
	}

	@Override
	public ProductDto save(ProductInputDto productDto) {
		return productRepository.save(productDto);
	}

	@Override
	public ProductDto delete(long id) {
		return productRepository.delete(id);
	}

}
