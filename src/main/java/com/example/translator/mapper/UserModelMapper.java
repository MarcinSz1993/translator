package com.example.translator.mapper;

import com.example.translator.dto.UserModelDto;
import com.example.translator.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserModelMapper {

    public static UserModelDto convertUserModelToUserModelDto(UserModel userModel){
        return new UserModelDto(
                userModel.getFirstName(),
                userModel.getLastName(),
                userModel.getUsername(),
                userModel.getPassword(),
                userModel.getEmail());
    }
}
