package com.example.reactivespringsimplecrud.repository;

import com.example.reactivespringsimplecrud.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByEmail(String email);
    Mono<Void> deleteByEmail(String email);
    Flux<User> findAll();
}
