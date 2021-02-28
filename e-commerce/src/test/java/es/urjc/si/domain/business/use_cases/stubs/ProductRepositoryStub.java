package es.urjc.si.domain.business.use_cases.stubs;

import java.util.ArrayList;
import java.util.Collection;

import es.urjc.si.domain.dtos.FullProductDto;
import es.urjc.si.domain.dtos.ProductInputDto;
import es.urjc.si.domain.exceptions.ProductNotFoundException;
import es.urjc.si.domain.ports.IProductRepository;

public class ProductRepositoryStub implements IProductRepository {

	ArrayList<FullProductDto> products;

	int index;

	public ProductRepositoryStub() {
		products = new ArrayList<>();
		index = 0;
	}

	@Override
	public Collection<FullProductDto> findAll() {
		return products;
	}

	@Override
	public FullProductDto findById(long id) {
		try {
			return products.get((int) id);
		} catch (IndexOutOfBoundsException e) {
			throw new ProductNotFoundException();
		}
	}

	@Override
	public FullProductDto save(ProductInputDto product) {
		FullProductDto p = FullProductDto.builder().id(Long.valueOf(index)).name(product.getName())
				.description(product.getDescription()).price(product.getPrice()).build();
		products.add(index, p);
		index++;

		return p;
	}

	@Override
	public FullProductDto delete(long id) {
		return products.remove((int) id);
	}
}