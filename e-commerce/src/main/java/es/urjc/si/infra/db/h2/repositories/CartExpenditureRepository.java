package es.urjc.si.infra.db.h2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.si.infra.db.h2.entities.CartExpenditure;

public interface CartExpenditureRepository extends JpaRepository<CartExpenditure, Long>{

}
