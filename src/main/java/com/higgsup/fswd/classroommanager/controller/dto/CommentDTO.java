package com.higgsup.fswd.classroommanager.controller.dto;

/**
 * Created by MinhTu on 5/14/2016.
 */
public class CommentDTO {
    private Long id;
    private String commentContent;
    private UserDTO1 userDTO1;

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public UserDTO1 getUserDTO1() {
        return userDTO1;
    }

    public void setUserDTO1(UserDTO1 userDTO1) {
        this.userDTO1 = userDTO1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
