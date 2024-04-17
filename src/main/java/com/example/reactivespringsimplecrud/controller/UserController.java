package com.example.reactivespringsimplecrud.controller;


import com.example.reactivespringsimplecrud.dto.UserDtoRequest;
import com.example.reactivespringsimplecrud.dto.UserDtoResponse;
import com.example.reactivespringsimplecrud.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Object> createUser(@RequestBody UserDtoRequest userDtoRequest){
        return userService.createUser(userDtoRequest);
    }
    @PostMapping("/read/{email}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<UserDtoResponse> readbyemail(@PathVariable String email){
        return userService.readByEmail(email);
    }
    @PostMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserDtoResponse> updateuser(@RequestBody UserDtoRequest userDtoRequest){
        return userService.updateUser(userDtoRequest);
    }
    @PostMapping("/delete/{email}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteusers(@PathVariable String email){
        return userService.deleteUser(email);
    }
    @GetMapping("/readall")
    @ResponseStatus(HttpStatus.OK)
    public Flux<UserDtoResponse> readall(){
        return userService.readAll();
    }
}
