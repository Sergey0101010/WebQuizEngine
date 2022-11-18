package com.sergey.webquizengine.controllers;

import com.sergey.webquizengine.businesslayer.RegistrationService;
import com.sergey.webquizengine.dto.UserDto;
import com.sergey.webquizengine.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
public class RegistrationController {

    private final RegistrationService registrationService;

    private final UserRepository userRepository;

    @PostMapping("api/register")
    public void registerNewUser(@RequestBody @Valid UserDto userDto) {
        registrationService.registerNewUser(userDto);
    }

    @GetMapping("api/delete/all")
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
