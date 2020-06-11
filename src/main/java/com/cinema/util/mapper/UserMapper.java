package com.cinema.util.mapper;

import com.cinema.model.User;
import com.cinema.model.dto.UserResponceDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponceDto parsingUserToDto(User user) {
        UserResponceDto userResponceDto = new UserResponceDto();
        userResponceDto.setId(user.getId());
        return userResponceDto;
    }
}
