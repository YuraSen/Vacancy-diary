package com.inmost.task.service.impl;

import com.inmost.task.domain.UserEntity;
import com.inmost.task.dto.User;
import com.inmost.task.exceprion.ExceptionUserIdNegative;
import com.inmost.task.exceprion.ExceptionUserNotExist;
import com.inmost.task.repository.UserRepository;
import com.inmost.task.service.UserService;
import com.inmost.task.service.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_BY_THIS_ID_NOT_EXIST = "User by this id not exist";
    private static final String ID_MUST_BE_POSITIVE = "Id must be positive";
    private static final int THE_SMALLEST_POSSIBLE_ID = 0;
    private static UserRepository userRepository;
    private static UserMapper userMapper;
    private static PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User edit(Long id, User user) {
        checkUserExist(id);

        user.setId(id);
        return save(user);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity = userMapper.userToUserEntity(user);
        userEntity = userRepository.save(userEntity);
        return userMapper.userEntityToUser(userEntity);
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
                .orElseThrow(() -> new ExceptionUserNotExist(USER_BY_THIS_ID_NOT_EXIST));
        return userMapper.userEntityToUser(userEntity);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Page<User> getPageUsers(int currentPage, int pageSize) {
        return null;
    }

    @Override
    public User usersVacancy(String nameVacancy) {
        return null;
    }

    private void checkUserExist(Long id) {
        if (userNotExistsById(id)) {
            throw new ExceptionUserNotExist();
        }
    }

    private void checkCorrectIdUser(Long id) {
        if (id < THE_SMALLEST_POSSIBLE_ID) {
            throw new ExceptionUserIdNegative(ID_MUST_BE_POSITIVE);
        }
    }

    private boolean userNotExistsById(Long id) {
        checkCorrectIdUser(id);

        return !userRepository.existsById(id);
    }
}
