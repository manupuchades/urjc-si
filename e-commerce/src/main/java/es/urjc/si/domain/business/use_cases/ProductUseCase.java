package es.urjc.si.domain.business.use_cases;

import java.util.Collection;

import es.urjc.si.domain.dtos.FullProductDto;
import es.urjc.si.domain.dtos.ProductInputDto;
import es.urjc.si.domain.ports.IProductRepository;
import es.urjc.si.domain.services.IProductService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductUseCase implements IProductService {

	IProductRepository productRepository;

	@Override
	public Collection<FullProductDto> findAll() {
		return productRepository.findAll();
	}

	@Override
	public FullProductDto findById(long id) {
		return productRepository.findById(id);
	}

	@Override
	public FullProductDto save(ProductInputDto productDto) {
		return productRepository.save(productDto);
	}

	@Override
	public FullProductDto delete(long id) {
		return productRepository.delete(id);
	}

}
