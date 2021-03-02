package es.urjc.si.infra.db.h2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.si.infra.db.h2.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
