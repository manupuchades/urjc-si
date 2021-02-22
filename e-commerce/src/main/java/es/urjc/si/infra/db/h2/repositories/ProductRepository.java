package es.urjc.si.infra.db.h2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.si.infra.db.h2.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
