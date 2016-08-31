package com.higgsup.fswd.classroommanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higgsup.fswd.classroommanager.controller.dto.UserDTO;
import com.higgsup.fswd.classroommanager.controller.stereotype.NoAuthentication;
import com.higgsup.fswd.classroommanager.controller.stereotype.RequiredRoles;
import com.higgsup.fswd.classroommanager.model.Role;
import com.higgsup.fswd.classroommanager.model.User;
import com.higgsup.fswd.classroommanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Tu on 4/24/2016.
 */
@RestController
@SessionAttributes
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    ObjectMapper mapper;

    @NoAuthentication
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public User createUser(@RequestBody UserDTO user) {

        return userService.createUser(user);
    }

    @NoAuthentication
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserDTO checkLogin(@RequestBody UserDTO user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("username", user.getUsername());
        return userService.doLogin(user);
    }

    @RequiredRoles({Role.STUDENT, Role.TEACHER})
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logOut(HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        userService.doLogout(token);
    }

    public static class StringContainer {
        public StringContainer(String value) {
            this.token = value;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        private String token;
    }

    @RequiredRoles({Role.STUDENT, Role.TEACHER})
    @RequestMapping(value = "/myclass", method = RequestMethod.GET)
    public UserDTO getClassesOfStudent(HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return userService.findUser(token);
    }
//    @RequiredRoles(Role.STUDENT)
//    @RequestMapping(value = "/mygroup",method = RequestMethod.GET)
//    public

}
