package com.higgsup.fswd.classroommanager.controller.dto;

import java.util.List;

/**
 * Created by MinhTu on 5/14/2016.
 */
public class PostDTO1 {
    private Long postId;
    private String postContent;
    private UserDTO1 userDTO1;
    private List<CommentDTO> commentDTOs;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public UserDTO1 getUserDTO1() {
        return userDTO1;
    }

    public void setUserDTO1(UserDTO1 userDTO1) {
        this.userDTO1 = userDTO1;
    }

    public List<CommentDTO> getCommentDTOs() {
        return commentDTOs;
    }

    public void setCommentDTOs(List<CommentDTO> commentDTOs) {
        this.commentDTOs = commentDTOs;
    }
}
