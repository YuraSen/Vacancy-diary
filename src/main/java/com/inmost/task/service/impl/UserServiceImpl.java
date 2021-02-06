package com.inmost.task.service.impl;

import com.inmost.task.domain.UserEntity;
import com.inmost.task.dto.StatusVacancy;
import com.inmost.task.dto.User;
import com.inmost.task.dto.Vacancy;
import com.inmost.task.exceprion.*;
import com.inmost.task.repository.UserRepository;
import com.inmost.task.service.UserService;
import com.inmost.task.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {

    public static final String NOT_SEND = "Email was not send";
    private static final String USER_BY_THIS_ID_NOT_EXIST = "User by this id not exist";
    private static final String ID_MUST_BE_POSITIVE = "Id must be positive";
    private static final int THE_SMALLEST_POSSIBLE_ID = 0;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registration(User user) {
        if (Objects.isNull(user)) {
            throw new InvalidDataRuntimeException("User is null");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserIsAlsoExistRuntimeException("User is exist");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity = userMapper.userToUserEntity(user);
        UserEntity saveEntity = userRepository.save(userEntity);

        return userMapper.userEntityToUser(saveEntity);
    }

    @Override
    public User findUser(String login, String password) {
        if (Objects.isNull(login) || Objects.isNull(password)) {
            throw new InvalidDataRuntimeException("User data for finding is uncorrected");
        }

        String encodedPassword = passwordEncoder.encode(password);
        Optional<UserEntity> userEntity = userRepository.findByEmail(login);
        User user = userMapper.userEntityToUser(userEntity
                .orElseThrow(() -> new EntityNotExistRuntimeException("User not found")));

        if (!user.getPassword().equals(encodedPassword)) {
            throw new ActionWithUserRuntimeException("User with this login and password is not exist");
        }

        return user;
    }

    @Override
    public User edit(User user) {

        return save(user);
    }

    @Override
    public void deleteById(Long id) {
        checkUserExist(id);

        userRepository.deleteById(id);
    }


    @Override
    public User findById(Long id) {
        checkCorrectIdUser(id);
        UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotExistRuntimeException(USER_BY_THIS_ID_NOT_EXIST));
        return userMapper.userEntityToUser(userEntity);
    }


    @Override
    public List<Vacancy> getUserVacancy(User user) {
        user = findById(user.getId());
        return new ArrayList<>(user.getVacancyList());
    }

    @Override
    public boolean sendTheEmail(String text, Long id) {
        User user = findById(id);
        LocalDate now = LocalDate.now();
        List<String> recruitersContacts = user.getVacancyList().stream()
                .filter(vacancy -> StatusVacancy.WAITING_FOR_FEEDBACK.equals(vacancy.getStatusVacancy()))
                .filter(vacancy -> isTimeOut(vacancy, now))
                .map(Vacancy::getRecruitersContacts)
                .collect(Collectors.toList());
        return globalSendEmails(recruitersContacts, text);

    }

    @Override
    public Page<User> getPageUsers(int currentPage, int pageSize) {
        PageRequest sortedByFirstName = PageRequest.of(currentPage - 1, pageSize, Sort.by("firstName"));
        Page<UserEntity> userEntities = userRepository.findAll(sortedByFirstName);
        List<User> result = userEntities
                .stream()
                .map(userMapper::userEntityToUser)
                .collect(Collectors.toList());
        return new PageImpl<>(result, sortedByFirstName, countUsers());
    }

    private User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity = userMapper.userToUserEntity(user);
        userEntity = userRepository.save(userEntity);
        return userMapper.userEntityToUser(userEntity);
    }

    private void checkUserExist(Long id) {
        if (userNotExistsById(id)) {
            throw new EntityNotExistRuntimeException();
        }
    }

    private void checkCorrectIdUser(Long id) {
        if (id < THE_SMALLEST_POSSIBLE_ID) {
            throw new EntityIdNegativeRuntimeException(ID_MUST_BE_POSITIVE);
        }
    }

    private boolean userNotExistsById(Long id) {
        checkCorrectIdUser(id);

        return !userRepository.existsById(id);
    }

    private long countUsers() {
        return userRepository.count();
    }

    private boolean isTimeOut(Vacancy vacancy, LocalDate now) {
        LocalDate compareData = now.minusDays(7);
        return compareData.isAfter(vacancy.getLastChange());
    }

    private boolean globalSendEmails(List<String> emails, String text) {
        try {
            emails.forEach(email -> sendEmail(email, text));
        } catch (Exception exception) {
            throw new EmailsUnSendRuntimeException(NOT_SEND, exception);
        }
        return true;
    }

    //TODO realized send email
    private void sendEmail(String email, String text) {

    }
}
