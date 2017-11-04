package org.world3.habit3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.world3.habit3.entity.User;
import org.world3.habit3.exception.ErrorCode;
import org.world3.habit3.exception.Habit3Exception;
import org.world3.habit3.exception.Habit3ValidationException;
import org.world3.habit3.repository.UserRepository;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ValidationService validationService;

    @Autowired
    UserRepository userRepository;

    public User create(User user) {
        validationService.validate(user);

        if (userRepository.findByUserId(user.getUserId()) != null) {
            throw new Habit3ValidationException(ErrorCode.UserExisting, "User already exist.");
        }

        try {
            user.setEncryptedPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList(User.Role.USER.name()));
            userRepository.save(user);
        }
        catch (DataAccessException dae) {
            throw new Habit3Exception(ErrorCode.GenericError, "Fail to persist user.", dae);
        }

        return user;
    }

    public User authenticateUser(String userName, String password) {
        User user = userRepository.findByUserId(userName);

        if (user != null && passwordEncoder.matches(password, user.getEncryptedPassword())) {
            return user;
        }
        else {
            return null;
        }
    }
}
