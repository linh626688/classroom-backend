package com.higgsup.fswd.classroommanager.controller;

import com.higgsup.fswd.classroommanager.controller.dto.LoginResultDTO;
import com.higgsup.fswd.classroommanager.controller.dto.StatusDTO;
import com.higgsup.fswd.classroommanager.controller.dto.UserDTO;
import com.higgsup.fswd.classroommanager.controller.stereotype.NoAuthentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @NoAuthentication
    @RequestMapping("/login")
    public LoginResultDTO login() {
        return new LoginResultDTO();
    }

    @RequestMapping("/logout")
    public StatusDTO logout() {
        return new StatusDTO();
    }

    @RequestMapping("/createUser")
    public UserDTO createUser(UserDTO userDTO) {
        return new UserDTO();
    }


}
