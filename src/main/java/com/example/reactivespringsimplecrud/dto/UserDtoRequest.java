package com.example.reactivespringsimplecrud.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDtoRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
