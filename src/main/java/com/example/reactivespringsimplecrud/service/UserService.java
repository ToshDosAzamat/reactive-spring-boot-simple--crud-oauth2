package com.example.reactivespringsimplecrud.service;

import com.example.reactivespringsimplecrud.dto.UserDtoRequest;
import com.example.reactivespringsimplecrud.dto.UserDtoResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<Object> createUser(UserDtoRequest userDtoRequest);
    Mono<UserDtoResponse> readByEmail(String email);
    Flux<UserDtoResponse> readAll();
    Mono<UserDtoResponse> updateUser(UserDtoRequest userDtoRequest);
    Mono<Void> deleteUser(String email);

}
