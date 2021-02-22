package es.urjc.si.domain.business.use_cases;

import java.util.ArrayList;
import java.util.Collection;

import es.urjc.si.domain.dtos.ProductDto;
import es.urjc.si.domain.dtos.ProductInputDto;
import es.urjc.si.domain.exceptions.ProductNotFoundException;
import es.urjc.si.domain.ports.IProductRepository;

public class ProductRepositoryStub implements IProductRepository {

	ArrayList<ProductDto> products;

	int index;

	ProductRepositoryStub() {
		products = new ArrayList<>();
		index = 0;
	}

	@Override
	public Collection<ProductDto> findAll() {
		return products;
	}

	@Override
	public ProductDto findById(long id) {
		try {
			return products.get((int) id);
		} catch (IndexOutOfBoundsException e) {
			throw new ProductNotFoundException();
		}
	}

	@Override
	public ProductDto save(ProductInputDto product) {
		ProductDto p = ProductDto.builder().id(Long.valueOf(index)).name(product.getName())
				.description(product.getDescription()).price(product.getPrice()).build();
		products.add(index, p);
		index++;

		return p;
	}

	@Override
	public ProductDto delete(long id) {
		return products.remove((int) id);
	}
}