package com.higgsup.fswd.classroommanager.controller.dto;

import java.util.List;

/**
 * Created by Long on 5/13/2016.
 */
public class GrouppDTO1 {
    private Long groupId;
    private String groupName;
    private List<UserDTO1> userDTO1s;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<UserDTO1> getUserDTO1s() {
        return userDTO1s;
    }

    public void setUserDTO1s(List<UserDTO1> userDTO1s) {
        this.userDTO1s = userDTO1s;
    }
}
