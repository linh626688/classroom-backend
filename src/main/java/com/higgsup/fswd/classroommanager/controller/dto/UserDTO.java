package com.higgsup.fswd.classroommanager.controller.dto;

import com.higgsup.fswd.classroommanager.model.Role;

import java.util.List;

/**
 * DTO = Data Transfer Object > luôn luôn là 1 object POJO = Plain Old Java Object
 * Chỉ có các fields và getter/setter
 * Created by lent on 4/20/2016.
 */

public class UserDTO {
    private String username;
    private String password;
    private Role role;
    private String fullName;
    private String token;

    private List<ClassRoomDTO> classRoomDTOs;
    private List<GrouppDTO> grouppDTOs;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<ClassRoomDTO> getClassRoomDTOs() {
        return classRoomDTOs;
    }

    public void setClassRoomDTOs(List<ClassRoomDTO> classRoomDTOs) {
        this.classRoomDTOs = classRoomDTOs;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<GrouppDTO> getGrouppDTOs() {
        return grouppDTOs;
    }

    public void setGrouppDTOs(List<GrouppDTO> grouppDTOs) {
        this.grouppDTOs = grouppDTOs;
    }
}
