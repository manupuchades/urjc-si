package es.urjc.si.infra.db.h2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.si.infra.db.h2.entities.ProductOrder;

public interface OrderRepository extends JpaRepository<ProductOrder, Long>{

	List<ProductOrder> findByShoppingCartAndProduct(Long shoppingCartId, Long productId);
	
	List<ProductOrder> findByShoppingCart(Long shoppingCartId);

}
