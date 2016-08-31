package com.higgsup.fswd.classroommanager.controller.dto;

/**
 * Created by hunghip on 4/27/2016.
 */
public class ClassRoomDTO {
    private Long classId;
    private String className;

    private UserDTO1 userDTO1;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public UserDTO1 getUserDTO1() {
        return userDTO1;
    }

    public void setUserDTO1(UserDTO1 userDTO1) {
        this.userDTO1 = userDTO1;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }
}
