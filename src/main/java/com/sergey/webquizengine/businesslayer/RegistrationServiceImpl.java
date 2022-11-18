package com.sergey.webquizengine.businesslayer;

import com.sergey.webquizengine.databaselayer.User;
import com.sergey.webquizengine.dto.UserDto;
import com.sergey.webquizengine.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void registerNewUser(UserDto user) {
        String userDtoEmail = user.getEmail();
        if (userRepository.existsByEmailIgnoreCase(userDtoEmail)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }
        User newUser = getNewUserFromDto(user);
        userRepository.save(newUser);
    }

    private User getNewUserFromDto(UserDto user) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole("ROLE_USER");
        return newUser;
    }
}
