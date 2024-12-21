package com.onchess.service;

import com.onchess.dto.LoginRequest;
import com.onchess.entity.User;
import com.onchess.dto.UserDto;
import com.onchess.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("Email is already registered");
        }

        userDto.setPassword(encodePassword(userDto.getPassword()));
        return userRepository.save(userDto.toUser());
    }

    private String encodePassword(String password) {
        // Add password encoding logic (e.g., BCrypt)
        return password; // Replace with actual encoding
    }
    public String login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        UserDto userDto = user.toUserDto();
        if (!userDto.getPassword().equals(loginRequest.getPassword())) { // Replace with password decoding logic
            throw new RuntimeException("Invalid username or password");
        }

        // Generate a token (e.g., JWT)
        return generateToken(user);
    }

    private String generateToken(User user) {
        // Token generation logic (e.g., JWT implementation)
        return "dummy-token"; // Replace with actual token logic
    }

    public User getUserById(Long id) {
        // Fetch user by ID
        Optional<User> user = userRepository.findUserById(id);
        if(user.isEmpty()){
            throw new RuntimeException("No user found");
        }
        return user.get();
    }
}