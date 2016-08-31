package com.higgsup.fswd.classroommanager.controller.dto;

import java.util.List;

/**
 * Created by Long on 5/13/2016.
 */
public class ClassRoomDTO1 {
    private Long classId;
    private String className;
    private UserDTO1 userDTO1;
    private List<UserDTO1> userDTO1s;

    public List<UserDTO1> getUserDTO1s() {
        return userDTO1s;
    }

    public void setUserDTO1s(List<UserDTO1> userDTO1s) {
        this.userDTO1s = userDTO1s;
    }

    public UserDTO1 getUserDTO1() {
        return userDTO1;
    }

    public void setUserDTO1(UserDTO1 userDTO1) {
        this.userDTO1 = userDTO1;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }
}
