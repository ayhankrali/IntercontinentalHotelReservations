package com.advanceacademy.moonlighthotel.service.user.impl;

import com.advanceacademy.moonlighthotel.converter.contact.UserConverter;
import com.advanceacademy.moonlighthotel.entity.user.User;
import com.advanceacademy.moonlighthotel.exception.ResourceNotFoundException;
import com.advanceacademy.moonlighthotel.payload.request.LoginRequest;
import com.advanceacademy.moonlighthotel.payload.request.SignupRequest;
import com.advanceacademy.moonlighthotel.payload.response.JwtResponse;
import com.advanceacademy.moonlighthotel.payload.response.UserInfoResponse;
import com.advanceacademy.moonlighthotel.repository.user.UserRepository;
import com.advanceacademy.moonlighthotel.service.user.UserService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    private final AuthenticationManager manager;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserInfoResponse createNewUser(SignupRequest request) {
        User user = userConverter.toUser(request);

        User savedUser = userRepository.save(user);

        return userConverter.toResponse(savedUser);
    }


    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with email %s not found", email)));
    }

    @Override
    public List<User> getUserByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("There is no users with that first name %s", firstName)));

    }

    @Override
    public List<User> getUserByLastName(String lastName) {
        return userRepository.findByLastName(lastName)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("There is no users with that first name %s", lastName)));
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User with phone number %s not found", phoneNumber)));
    }

    @Override
    public User updateUser(Long userId,User updatedUser) {
        User extantUser = userRepository.findById(userId).orElse(null);

        if (extantUser != null) {
            extantUser.setFirstName(updatedUser.getFirstName());
        }
        if (extantUser != null) {
            extantUser.setLastName(updatedUser.getLastName());
        }
        if (extantUser != null) {
            extantUser.setEmail(updatedUser.getEmail());
        }
        if (extantUser != null){
            extantUser.setPhoneNumber(updatedUser.getPhoneNumber());
        }
        if (extantUser != null){
            extantUser.setUserRole(updatedUser.getUserRole());
        }

        return userRepository.save(extantUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}
