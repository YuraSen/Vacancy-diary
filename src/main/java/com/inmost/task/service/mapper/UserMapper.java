package com.inmost.task.service.mapper;

import com.inmost.task.domain.UserEntity;
import com.inmost.task.domain.VacancyEntity;
import com.inmost.task.dto.User;
import com.inmost.task.dto.Vacancy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {

    private VacancyMapper vacancyMapper;

    public User userEntityToUser(UserEntity userEntity) {
        if (Objects.isNull(userEntity)) {
            return null;
        }
        List<Vacancy> vacancyList = userEntity.getVacancyEntityList().stream()
                .map(vacancyEntity -> vacancyMapper.vacancyEntityToVacancy(vacancyEntity))
                .collect(Collectors.toList());
        return User.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .birthday(userEntity.getBirthday())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .description(userEntity.getDescription())
                .vacancyList(vacancyList)
                .build();
    }

    public UserEntity userToUserEntity(User user) {
        if (Objects.isNull(user)) {
            return null;
        }

        List<VacancyEntity> vacancyEntities = user.getVacancyList().stream()
                .map(vacancy -> vacancyMapper.vacancyToVacancyEntity(vacancy))
                .collect(Collectors.toList());

        return UserEntity.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthday(user.getBirthday())
                .email(user.getEmail())
                .password(user.getPassword())
                .description(user.getDescription())
                .vacancyEntityList(vacancyEntities)
                .build();
    }

}
