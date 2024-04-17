package com.example.reactivespringsimplecrud.service.impelement;


import com.example.reactivespringsimplecrud.dto.UserDtoRequest;
import com.example.reactivespringsimplecrud.dto.UserDtoResponse;
import com.example.reactivespringsimplecrud.mapper.UserMapper;
import com.example.reactivespringsimplecrud.model.User;
import com.example.reactivespringsimplecrud.repository.UserRepository;
import com.example.reactivespringsimplecrud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserServiceImpelement implements UserService {
    private UserRepository userRepository;

    public UserServiceImpelement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public Mono<Object> createUser(UserDtoRequest userDtoRequest) {
        User user = UserMapper.userDtoRequestToUser(userDtoRequest);
        return userRepository.findByEmail(userDtoRequest.getEmail())
                .flatMap(existingUser -> Mono.error(new RuntimeException("Email already register")))
                .switchIfEmpty(userRepository.save(user));
    }

    @Override
    public Mono<UserDtoResponse> readByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> UserMapper.userToUserDtoResponse(user))
                .switchIfEmpty(Mono.error(new RuntimeException("User not found!")));
    }

    @Override
    public Flux<UserDtoResponse> readAll() {
        return userRepository.findAll()
                .map((user)-> UserMapper.userToUserDtoResponse(user))
                .switchIfEmpty(Mono.error(new RuntimeException("Users not found!")));
    }

    @Override
    public Mono<UserDtoResponse> updateUser(UserDtoRequest userDtoRequest) {
        return userRepository.findByEmail(userDtoRequest.getEmail())
                .flatMap(newUser -> {
                    newUser.setFistName(userDtoRequest.getFirstName());
                    newUser.setLastName(userDtoRequest.getLastName());
                    newUser.setEmail(userDtoRequest.getEmail());
                    newUser.setPassword(userDtoRequest.getPassword());
                    return userRepository.save(newUser);
                })
                .map(updatedUser -> UserMapper.userToUserDtoResponse(updatedUser))
                .switchIfEmpty(Mono.error(new RuntimeException("User not found!")));
    }

    @Override
    public Mono<Void> deleteUser(String email) {
        return userRepository.deleteByEmail(email);
    }
}
