package es.urjc.si.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.si.models.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
