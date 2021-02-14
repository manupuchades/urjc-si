package es.urjc.si.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import es.urjc.si.models.Provincia;

public interface ProvinciaRepository extends MongoRepository<Provincia, String>{

}
