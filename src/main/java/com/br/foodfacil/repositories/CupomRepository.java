package com.br.foodfacil.repositories;

import com.br.foodfacil.models.Cupom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CupomRepository extends MongoRepository<Cupom, String> {

}
