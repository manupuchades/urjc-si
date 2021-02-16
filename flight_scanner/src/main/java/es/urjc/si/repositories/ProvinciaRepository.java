package es.urjc.si.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import es.urjc.si.dtos.ProvinciasPorComunidadDto;
import es.urjc.si.models.Provincia;

public interface ProvinciaRepository extends MongoRepository<Provincia, String>{
	
	@Aggregation(pipeline = {
			"{ $group : { _id: '$CA', provincias: { $sum: 1 }}}" ,
			"{ $sort: { _id: 1}}" , 
			"{ $project: { _id: {$ifNull: ['$_id', 'sin comunidad']}, provincias: 1}}"})
	List<ProvinciasPorComunidadDto> countProvinciasByComunidad();

}
