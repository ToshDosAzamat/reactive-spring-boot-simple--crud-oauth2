package com.example.reactivespringsimplecrud.mapper;

import com.example.reactivespringsimplecrud.dto.UserDtoRequest;
import com.example.reactivespringsimplecrud.dto.UserDtoResponse;
import com.example.reactivespringsimplecrud.model.User;

public class UserMapper {
    public static UserDtoResponse userToUserDtoResponse(User user){
        UserDtoResponse userDtoResponse = new UserDtoResponse();
        userDtoResponse.setFirstName(user.getFistName());
        userDtoResponse.setLastName(user.getLastName());
        userDtoResponse.setEmail(user.getEmail());
        return userDtoResponse;
    }
    public static User userDtoRequestToUser(UserDtoRequest userDtoRequest){
        User user = new User();
        user.setFistName(userDtoRequest.getFirstName());
        user.setLastName(userDtoRequest.getLastName());
        user.setEmail(userDtoRequest.getEmail());
        user.setPassword(userDtoRequest.getPassword());
        return user;
    }
}
