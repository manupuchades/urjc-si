package es.urjc.si;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.urjc.si.domain.business.use_cases.ProductUseCase;
import es.urjc.si.domain.business.use_cases.ShoppingCartUseCase;
import es.urjc.si.domain.ports.IProductRepository;
import es.urjc.si.domain.ports.IShoppingCartPublisher;
import es.urjc.si.domain.ports.IShoppingCartRepository;
import es.urjc.si.domain.ports.IShoppingCartValidator;
import es.urjc.si.domain.services.IProductService;
import es.urjc.si.domain.services.IShoppingCartService;

@Configuration
public class ECommerceConfig {

	@Bean
	public IProductService bookUseCase(IProductRepository repositoryAdapter) {
		return new ProductUseCase(repositoryAdapter);
	}

	@Bean
	public IShoppingCartService shoppingCartUseCase(IShoppingCartRepository shoppingCartRepositoryAdapter,
			IProductRepository productRepositoryAdapter, IShoppingCartValidator shoppingCartValidator, 	IShoppingCartPublisher shoppingCartPublisher) {
		return new ShoppingCartUseCase(shoppingCartRepositoryAdapter, productRepositoryAdapter, shoppingCartValidator, shoppingCartPublisher);
	}

}
