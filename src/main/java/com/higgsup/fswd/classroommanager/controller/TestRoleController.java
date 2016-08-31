package com.higgsup.fswd.classroommanager.controller;

import com.higgsup.fswd.classroommanager.controller.stereotype.NoAuthentication;
import com.higgsup.fswd.classroommanager.controller.stereotype.RequiredRoles;
import com.higgsup.fswd.classroommanager.model.Role;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lent on 4/20/2016.
 */
@RestController
public class TestRoleController {
    @NoAuthentication
    @RequestMapping("/isGuest")
    public String isGuest() {
        return "Yes, guest is accessible";
    }

    @RequiredRoles(Role.STUDENT)
    @RequestMapping("/isStudent")
    public String isStudent() {
        return "Yes, student is accessible";
    }

    @RequiredRoles(Role.TEACHER)
    @RequestMapping("/isTeacher")
    public String isTeacher() {
        return "Yes, teacher is accessible";
    }

//    @RequiredRoles({"Administrator", "Registered"})
//    @RequestMapping("/isNotGuest")
//    public String isNotGuest() {
//        return "Yes, user in the system is accessible";
//    }
//
//
//    @RequiredRoles("login")
//    @RequestMapping("/student")
//    public String isStudent() {
//        return "Yes, registered user is accessible";
//    }
}
