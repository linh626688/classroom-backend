package com.higgsup.fswd.classroommanager.controller;

import com.higgsup.fswd.classroommanager.controller.dto.PostDTO;
import com.higgsup.fswd.classroommanager.controller.dto.PostDTO1;
import com.higgsup.fswd.classroommanager.controller.stereotype.RequiredRoles;
import com.higgsup.fswd.classroommanager.model.Role;
import com.higgsup.fswd.classroommanager.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by MinhTu on 5/14/2016.
 */
@RestController
public class PostController {
    @Autowired
    private PostService postService;

    //teacher create post in class
    @RequiredRoles(Role.TEACHER)
    @RequestMapping(value = "/classes/{class_id}/posts", method = RequestMethod.POST)
    public PostDTO createPostInClass(@PathVariable("class_id") Long id, HttpServletRequest request, @RequestBody PostDTO postDTO) {
        String token = request.getHeader("auth-token");
        return postService.createPostInClass(id, token, postDTO);
    }

    //student create post in group
    @RequiredRoles(Role.STUDENT)
    @RequestMapping(value = "/groups/{group_id}/posts", method = RequestMethod.POST)
    public PostDTO createPostInGroup(@PathVariable("group_id") Long groupId, @RequestBody PostDTO postDTO, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return postService.createPostInGroup(groupId, postDTO, token);
    }

    //get list post trong classroom
    @RequiredRoles({Role.TEACHER, Role.STUDENT})
    @RequestMapping(value = "/classes/{class_id}/posts", method = RequestMethod.GET)
    public List<PostDTO> getPostsInClass(@PathVariable("class_id") Long id, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return postService.getPostsInClass(id, token);
    }

    //get list post trong group
    @RequiredRoles(Role.STUDENT)
    @RequestMapping(value = "/groups/{group_id}/posts", method = RequestMethod.GET)
    public List<PostDTO> getPostsInGroup(@PathVariable("group_id") Long groupId, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return postService.getPostsInGroup(groupId, token);
    }

    //show a DETAILED post in classroom
    @RequiredRoles({Role.STUDENT, Role.TEACHER})
    @RequestMapping(value = "/posts/{post_id}", method = RequestMethod.GET)
    public PostDTO1 getDetailOfPostInClass(@PathVariable("post_id") Long postId, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return postService.getPostDetail(postId, token);
    }
//LONGGGGGG

    //User edit a post
    @RequiredRoles({Role.TEACHER, Role.STUDENT})
    @RequestMapping(value = "posts/{post_id}", method = RequestMethod.PUT)
    public PostDTO editPost(@PathVariable("post_id") Long postId, @RequestBody PostDTO postDTO, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return postService.editPost(postId, postDTO, token);
    }

    //User delete a post
    @RequiredRoles({Role.TEACHER, Role.STUDENT})
    @RequestMapping(value = "posts/{post_id}", method = RequestMethod.DELETE)
    public String deletePost(@PathVariable("post_id") Long postId, HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        return postService.deletePost(postId, token);
    }
}
