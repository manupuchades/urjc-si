package es.urjc.si.infra.db.h2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.si.infra.db.h2.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findByShoppingCartAndProduct(Long shoppingCartId, Long productId);
	
	List<Order> findByShoppingCart(Long shoppingCartId);

}
