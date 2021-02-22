package es.urjc.si.infra.db.h2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.si.infra.db.h2.entities.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>{

}
