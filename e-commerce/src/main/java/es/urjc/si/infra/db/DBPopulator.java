package es.urjc.si.infra.db;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import es.urjc.si.domain.dtos.ProductInputDto;
import es.urjc.si.domain.ports.IProductRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DBPopulator {

	IProductRepository products;
	
	@PostConstruct
	public void populate() {
		
		products.save(ProductInputDto.builder().name("Popitas al punto de sal").description("Palomitas de maiz sabor natural con sal para microondas").price(Double.valueOf("2.15")).build());
		products.save(ProductInputDto.builder().name("Ruffles Jamón").description("Patatas fritas onduladas con sabor a jamón").price(Double.valueOf("1.48")).build());
		products.save(ProductInputDto.builder().name("LAY's Gourmet").description("Patatas fritas lisas crujientes").price(Double.valueOf("2.14")).build());
		products.save(ProductInputDto.builder().name("Cheetos Pandilla").description("Snack de fantasmas con sabor a queso").price(Double.valueOf("1.19")).build());
		products.save(ProductInputDto.builder().name("Boca Bits").description("Snack de cortezas de trigo con sabor a carne").price(Double.valueOf("1.47")).build());
		
	}
	
}
