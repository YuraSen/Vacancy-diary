package com.inmost.task.service.mapper;

import com.inmost.task.domain.UserEntity;
import com.inmost.task.dto.User;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component

public class UserMapper {
    public User userEntityToUser(UserEntity userEntity) {
        if (Objects.isNull(userEntity)) {
            return null;
        }

        return User.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .birthday(userEntity.getBirthday())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .description(userEntity.getDescription())
                .vacancyList(userEntity.getVacancyList())
                .build();
    }

    public UserEntity userToUserEntity(User user) {
        if (Objects.isNull(user)) {
            return null;
        }

        return UserEntity.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthday(user.getBirthday())
                .email(user.getEmail())
                .password(user.getPassword())
                .description(user.getDescription())
                .vacancyList(user.getVacancyList())
                .build();
    }
}
