package es.urjc.si.infra.db;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import es.urjc.si.domain.dtos.FullProductDto;
import es.urjc.si.domain.dtos.FullShoppingCartDto;
import es.urjc.si.domain.dtos.OrderInputDto;
import es.urjc.si.domain.dtos.ProductInputDto;
import es.urjc.si.domain.dtos.ShoppingCartInputDto;
import es.urjc.si.domain.ports.IProductRepository;
import es.urjc.si.domain.ports.IShoppingCartRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DBPopulator {

	IProductRepository products;
	
	IShoppingCartRepository shoppingCart;
	
	@PostConstruct
	public void populate() {
		
		FullProductDto popitas = products.save(ProductInputDto.builder().name("Popitas al punto de sal").description("Palomitas de maiz sabor natural con sal para microondas").price(Double.valueOf("2.15")).build());
		FullProductDto ruffles = products.save(ProductInputDto.builder().name("Ruffles Jamón").description("Patatas fritas onduladas con sabor a jamón").price(Double.valueOf("1.48")).build());
		FullProductDto gourmet = products.save(ProductInputDto.builder().name("LAY's Gourmet").description("Patatas fritas lisas crujientes").price(Double.valueOf("2.14")).build());
		FullProductDto cheetos = products.save(ProductInputDto.builder().name("Cheetos Pandilla").description("Snack de fantasmas con sabor a queso").price(Double.valueOf("1.19")).build());
		FullProductDto bocaBits = products.save(ProductInputDto.builder().name("Boca Bits").description("Snack de cortezas de trigo con sabor a carne").price(Double.valueOf("1.47")).build());
		
		FullShoppingCartDto user01ShoppingCart = shoppingCart.save(ShoppingCartInputDto.builder().customer("User01").build());
		FullShoppingCartDto user02ShoppingCart = shoppingCart.save(ShoppingCartInputDto.builder().customer("User02").build());
		
		shoppingCart.addOrder(OrderInputDto.builder().productId(bocaBits.getId()).quantity(1).shoppingCartId(user01ShoppingCart.getId()).build());
		shoppingCart.addOrder(OrderInputDto.builder().productId(ruffles.getId()).quantity(2).shoppingCartId(user01ShoppingCart.getId()).build());
		shoppingCart.addOrder(OrderInputDto.builder().productId(gourmet.getId()).quantity(3).shoppingCartId(user01ShoppingCart.getId()).build());
		shoppingCart.addOrder(OrderInputDto.builder().productId(bocaBits.getId()).quantity(4).shoppingCartId(user01ShoppingCart.getId()).build());

		
	}
	
}
