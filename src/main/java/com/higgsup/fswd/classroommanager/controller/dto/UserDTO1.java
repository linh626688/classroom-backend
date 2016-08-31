package com.higgsup.fswd.classroommanager.controller.dto;

import com.higgsup.fswd.classroommanager.model.Role;

/**
 * Created by Long on 5/13/2016.
 */
public class UserDTO1 {
    private String username;
    private Role role;
    private String fullName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

