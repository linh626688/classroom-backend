package com.higgsup.fswd.classroommanager.controller;

import com.higgsup.fswd.classroommanager.controller.dto.CommentDTO;
import com.higgsup.fswd.classroommanager.controller.stereotype.RequiredRoles;
import com.higgsup.fswd.classroommanager.model.Role;
import com.higgsup.fswd.classroommanager.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Long on 5/15/2016.
 */
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    //User creates a comment in a post
    @RequiredRoles({Role.STUDENT, Role.TEACHER})
    @RequestMapping(value = "/posts/{post_id}/comments", method = RequestMethod.POST)
    public CommentDTO createCommentInPost(@PathVariable("post_id") Long postId, @RequestBody CommentDTO commentDTO, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return commentService.createCommentInPost(token, postId, commentDTO);
    }

    //LONGGGGGG
//
//
//
    @RequiredRoles({Role.STUDENT, Role.TEACHER})
    @RequestMapping(value = "comments/{comment_id}", method = RequestMethod.PUT)
    public CommentDTO editCommentInPost(@PathVariable("comment_id") Long commentId, @RequestBody CommentDTO commentDTO, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return commentService.editCommentInPost(token, commentId, commentDTO);
    }

    //LONGGGGGG
//
//
//
    @RequiredRoles({Role.TEACHER, Role.STUDENT})
    @RequestMapping(value = "comments/{comment_id}", method = RequestMethod.DELETE)
    public String deleteComment(@PathVariable("comment_id") Long commentId, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return commentService.deleteComment(commentId, token);
    }


}
