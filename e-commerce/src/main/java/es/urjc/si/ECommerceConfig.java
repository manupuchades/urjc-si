package es.urjc.si;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.urjc.si.domain.business.use_cases.ProductUseCase;
import es.urjc.si.domain.ports.IProductRepository;
import es.urjc.si.domain.services.IProductService;

@Configuration
public class ECommerceConfig {
	
	@Bean
	public IProductService bookUseCase(IProductRepository repositoryAdapter) {
		return new ProductUseCase(repositoryAdapter);
	}

}
