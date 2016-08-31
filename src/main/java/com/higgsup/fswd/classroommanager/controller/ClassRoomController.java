package com.higgsup.fswd.classroommanager.controller;

import com.higgsup.fswd.classroommanager.controller.dto.ClassRoomDTO;
import com.higgsup.fswd.classroommanager.controller.dto.ClassRoomDTO1;
import com.higgsup.fswd.classroommanager.controller.stereotype.NoAuthentication;
import com.higgsup.fswd.classroommanager.controller.stereotype.RequiredRoles;
import com.higgsup.fswd.classroommanager.model.Role;
import com.higgsup.fswd.classroommanager.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by MinhTu on 5/9/2016.
 */
@RestController
public class ClassRoomController {
    @Autowired
    private ClassRoomService classRoomService;

    //get ALL classRoom
    @NoAuthentication
    @RequestMapping(value = "/allclasses", method = RequestMethod.GET)
    public List<ClassRoomDTO> getAllClassRoom() {
        return classRoomService.findClassRooms();
    }

    @RequiredRoles(Role.STUDENT)
    @RequestMapping(value = "/notEnrollClasses", method = RequestMethod.GET)
    public List<ClassRoomDTO> getNotEnrollClasses(HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return classRoomService.getNotEnrollClasses(token);
    }

    //teacher create  1 classroom
    @RequiredRoles(Role.TEACHER)
    @RequestMapping(value = "/classes", method = RequestMethod.POST)
    public ClassRoomDTO createClassRoom(@RequestBody ClassRoomDTO classRoomDTO, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return classRoomService.createClassRoom(classRoomDTO, token);
    }

    //teacher delete 1 classroom    `
    @RequiredRoles(Role.TEACHER)
    @RequestMapping(value = "/classes/{class_id}", method = RequestMethod.DELETE)
    public String deleteClassRoom(@PathVariable("class_id") Long id, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return classRoomService.deleteClassRoom(id, token);
    }

    //get list classroom by user create
    @RequiredRoles(Role.TEACHER)
    @RequestMapping(value = "/classes", method = RequestMethod.GET)
    public List<ClassRoomDTO> getClassOfTeacher(HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return classRoomService.getClassRoomOfTeacher(token);
    }

    //add a student into class
    @RequiredRoles(Role.STUDENT)
    @RequestMapping(value = "classes/{class_id}", method = RequestMethod.POST)
    public String addUserToClassRoom(@PathVariable("class_id") Long id, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return classRoomService.addUserToClassRoom(id, token);
    }

    // student leaves class
    @RequiredRoles(Role.STUDENT)
    @RequestMapping(value = "/classes/{class_id}/remove", method = RequestMethod.DELETE)
    public String deleteStudentFromClassRoom(@PathVariable("class_id") Long id, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return classRoomService.deleteStudent(id, token);
    }

    //get 1 class of student (class detail)
    @RequiredRoles({Role.STUDENT, Role.TEACHER})
    @RequestMapping(value = "classes/{class_id}", method = RequestMethod.GET)
    public ClassRoomDTO1 getClassDeTail(@PathVariable("class_id") Long id) {
        return classRoomService.getClassDetail(id);
    }

    //teacher update classroom
    @RequiredRoles(Role.TEACHER)
    @RequestMapping(value = "/classes/{class_id}", method = RequestMethod.PUT)
    public ClassRoomDTO editClassRoom(@PathVariable("class_id") Long id, @RequestBody ClassRoomDTO classRoomDTO, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return classRoomService.editClassRoom(id, classRoomDTO, token);
    }
}
