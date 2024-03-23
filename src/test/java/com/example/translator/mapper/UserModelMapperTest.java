package com.example.translator.mapper;

import com.example.translator.dto.UserModelDto;
import com.example.translator.model.Role;
import com.example.translator.model.UserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserModelMapperTest {
    @Test
    public void shouldMapUserModelToUserModelDto(){
        UserModel userModel = new UserModel(
                1,
                "Jan",
                "Kowalski",
                "Kowal",
                "qwerty",
                "jan@kowalski.pl",
                Role.USER,
                null
                );

        UserModelDto expectedUserModelDto = new UserModelDto(
                "Jan",
                "Kowalski",
                "Kowal",
                "qwerty",
                "jan@kowalski.pl");

        UserModelDto actualUserModelDto = UserModelMapper.convertUserModelToUserModelDto(userModel);

        Assertions.assertEquals(expectedUserModelDto,actualUserModelDto);
    }
    @Test
    public void shouldThrowNullPointerExceptionWhenUserModelIsNull(){

        Assertions.assertThrows(NullPointerException.class, () -> UserModelMapper.convertUserModelToUserModelDto(null));
    }
}
