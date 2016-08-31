package com.higgsup.fswd.classroommanager.controller;

import com.higgsup.fswd.classroommanager.controller.dto.GrouppDTO;
import com.higgsup.fswd.classroommanager.controller.dto.GrouppDTO1;
import com.higgsup.fswd.classroommanager.controller.stereotype.RequiredRoles;
import com.higgsup.fswd.classroommanager.model.Role;
import com.higgsup.fswd.classroommanager.service.GrouppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Long on 5/13/2016.
 */

@RestController
public class GrouppController {
    @Autowired
    private GrouppService grouppService;

    //teacher create 1 group
    @RequiredRoles(Role.TEACHER)
    @RequestMapping(value = "/classes/{class_id}/groups", method = RequestMethod.POST)
    public GrouppDTO createGroupp(@PathVariable("class_id") Long id, @RequestBody GrouppDTO grouppDTO, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return grouppService.createGroupp(id, grouppDTO, token);
    }

    //teacher delete 1 group
    @RequiredRoles(Role.TEACHER)
    @RequestMapping(value = "/groups/{group_id}/removeGroup", method = RequestMethod.DELETE)
    public String deleteGroupp(@PathVariable("group_id") Long groupId, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return grouppService.deleteGroupp(groupId, token);
    }

    //show all groups in one class
    @RequiredRoles({Role.STUDENT, Role.TEACHER})
    @RequestMapping(value = "/classes/{class_id}/groups", method = RequestMethod.GET)
    public List<GrouppDTO> getGroupps(@PathVariable("class_id") Long id, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return grouppService.getGroupps(id, token);
    }

    //add a user into a group
    @RequiredRoles(Role.STUDENT)
    @RequestMapping(value = "/groups/{group_id}/user", method = RequestMethod.POST)
    public String addUserToGroup(@PathVariable("group_id") Long groupId, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return grouppService.addUserToGroup(token, groupId);
    }

    //user leaves group
    @RequiredRoles(Role.STUDENT)
    @RequestMapping(value = "/groups/{group_id}/removeUser", method = RequestMethod.DELETE)
    public String deleteUserFromGroup(@PathVariable("group_id") Long groupId, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return grouppService.deleteUserFromGroup(token, groupId);
    }

    //get group detail
    @RequiredRoles({Role.STUDENT, Role.TEACHER})
    @RequestMapping(value = "/groups/{group_id}/user", method = RequestMethod.GET)
    public GrouppDTO1 getGroupDetail(@PathVariable("group_id") Long groupId, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return grouppService.getGroupDetail(token, groupId);
    }

    //Teacher edit a group
    @RequiredRoles(Role.TEACHER)
    @RequestMapping(value = "/groups/{group_id}", method = RequestMethod.PUT)
    public GrouppDTO editGroup(@PathVariable("group_id") Long groupId, @RequestBody GrouppDTO grouppDTO, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return grouppService.editGroup(groupId, grouppDTO, token);
    }

    //get group not enroll
    @RequiredRoles(Role.STUDENT)
    @RequestMapping(value = "/classes/{class_id}/groups/notEnrollGroup",method = RequestMethod.GET)
    public List<GrouppDTO> getNotEnrollGroup(@PathVariable("class_id") Long classId,HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return grouppService.getNotEnrollGroup(classId,token);
    }
    @RequiredRoles(Role.STUDENT)
    @RequestMapping(value = "/classes/{class_id}/groups/EnrollGroup",method = RequestMethod.GET)
    public List<GrouppDTO> getEnrollGroup(@PathVariable("class_id") Long classId,HttpServletRequest request){
        String token = request.getHeader("auth-token");
        return grouppService.getEnrollGroup(classId,token);
    }


}
