package com.sergey.webquizengine.businesslayer;

import com.sergey.webquizengine.dto.UserDto;

public interface RegistrationService {

    void registerNewUser(UserDto user);
}
