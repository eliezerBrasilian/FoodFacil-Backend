package com.br.foodfacil.ff.repositories;

import com.br.foodfacil.ff.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;


public interface UserRepository extends MongoRepository<User, Long> {
    @Query("{ 'email' : ?0 }")
    Optional<User> findByEmail(String email);
}
