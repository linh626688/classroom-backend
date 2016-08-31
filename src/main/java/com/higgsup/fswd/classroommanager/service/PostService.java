package com.higgsup.fswd.classroommanager.service;

import com.higgsup.fswd.classroommanager.controller.dto.CommentDTO;
import com.higgsup.fswd.classroommanager.controller.dto.PostDTO;
import com.higgsup.fswd.classroommanager.controller.dto.PostDTO1;
import com.higgsup.fswd.classroommanager.controller.dto.UserDTO1;
import com.higgsup.fswd.classroommanager.model.*;
import com.higgsup.fswd.classroommanager.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MinhTu on 5/14/2016.
 */
@Service
public class PostService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClassRoomRepository classRoomRepository;
    @Autowired
    private GrouppRepository grouppRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;


    public PostDTO createPostInClass(Long id, String token, PostDTO postDTO) {
        User user = userRepository.findByToken(token);
        ClassRoom classRoom = classRoomRepository.findById(id);
        PostDTO postDTO1 = new PostDTO();
        if (classRoom.getUser() == user) {
            //create
            Post post = new Post();
            post.setPostContent(postDTO.getPostContent());
            post.setUser(user);
            post.setClassRoom(classRoom);
            post = postRepository.save(post);
            classRoom.getPosts().add(post);
            classRoomRepository.save(classRoom);
            user.getPosts().add(post);
            userRepository.save(user);


            UserDTO1 userDTO1 = new UserDTO1();
            userDTO1.setUsername(user.getUsername());
            userDTO1.setFullName(user.getFullName());
            userDTO1.setRole(user.getRole());
            postDTO1.setPostContent(post.getPostContent());
            postDTO1.setUserDTO1(userDTO1);
            postDTO1.setPostId(post.getId());
            return postDTO1;
        } else {
            throw new NullPointerException("ko phai teacher cua class");
        }
    }

    public PostDTO createPostInGroup(Long groupId, PostDTO postDTO, String token) {
        User user = userRepository.findByToken(token);

        Groupp groupp = grouppRepository.findById(groupId);
        PostDTO postDTO1 = new PostDTO();

        if (groupp.getUsers().contains(user)) {
            //create
            Post post = new Post();
            post.setPostContent(postDTO.getPostContent());
            post.setUser(user);
            post.setGroupp(groupp);
            post = postRepository.save(post);
            user.getPosts().add(post);
            userRepository.save(user);
            groupp.getPosts().add(post);
            grouppRepository.save(groupp);

            postDTO1.setPostContent(post.getPostContent());
            UserDTO1 userDTO1 = new UserDTO1();
            userDTO1.setUsername(user.getUsername());
            userDTO1.setFullName(user.getFullName());
            userDTO1.setRole(user.getRole());
            postDTO1.setUserDTO1(userDTO1);
            postDTO1.setPostId(post.getId());
            return postDTO1;
        } else {
            throw new NullPointerException("ko ơ trong group");
        }
    }

    public List<PostDTO> getPostsInClass(Long id, String token) {
        User user = userRepository.findByToken(token);
        ClassRoom classRoom = classRoomRepository.findById(id);
        List<PostDTO> postDTOs = new ArrayList<PostDTO>();
        if (classRoom.getUsers().contains(user)) {
            //get
            List<Post> posts = classRoom.getPosts();
            for (Post post : posts) {
                PostDTO postDTO = new PostDTO();
                postDTO.setPostContent(post.getPostContent());
                UserDTO1 userDTO1 = new UserDTO1();
                userDTO1.setUsername(post.getUser().getUsername());
                userDTO1.setFullName(post.getUser().getFullName());
                userDTO1.setRole(post.getUser().getRole());
                postDTO.setUserDTO1(userDTO1);
                postDTO.setPostId(post.getId());
                postDTOs.add(postDTO);
            }
            return postDTOs;
        } else {
            throw new NullPointerException("ko ơ trong class");
        }

    }

    public List<PostDTO> getPostsInGroup(Long groupId, String token) {
        User user = userRepository.findByToken(token);
        Groupp groupp = grouppRepository.findById(groupId);
        List<PostDTO> postDTOs = new ArrayList<PostDTO>(); //can lay
        if (groupp.getUsers().contains(user)) {
            //get
            List<Post> posts = groupp.getPosts();
            for (Post post : posts) {
                PostDTO postDTO = new PostDTO();
                postDTO.setPostContent(post.getPostContent());
                postDTO.setPostId(post.getId());
                UserDTO1 userDTO1 = new UserDTO1();
                userDTO1.setUsername(post.getUser().getUsername());
                userDTO1.setFullName(post.getUser().getFullName());
                userDTO1.setRole(post.getUser().getRole());
                postDTO.setUserDTO1(userDTO1);
                postDTOs.add(postDTO);
            }
            return postDTOs;
        } else {
            throw new NullPointerException("ko ơ trong group");
        }
    }

//LONGGGGGG

    public PostDTO editPost(Long postId, PostDTO postDTO, String token) {
        User user = userRepository.findByToken(token);
        Post post = postRepository.findById(postId);
        PostDTO postDTO1 = new PostDTO();

        if (post.getClassRoom() != null && post.getGroupp() == null) {
            if (post.getUser() == user) {
                //TEACHER edits post content
                post.setPostContent(postDTO.getPostContent());
                post = postRepository.save(post);
                postDTO1.setPostContent(post.getPostContent());
            } else {
                throw new NullPointerException("ko phai user nay viet ");
            }
        }

        if (post.getClassRoom() == null && post.getGroupp() != null) {
            if (post.getUser() == user) {
                //STUDENT edits post content
                post.setPostContent(postDTO.getPostContent());
                post = postRepository.save(post);
                postDTO1.setPostContent(post.getPostContent());
            } else {
                throw new NullPointerException("ko phai user nay viet ");
            }
        }

        return postDTO1;
    }

    @Transactional(readOnly = false)
    public String deletePost(Long postId, String token) {
        User user = userRepository.findByToken(token);
        Post post = postRepository.findById(postId);
        ClassRoom classRoom = post.getClassRoom();
        Groupp groupp =post.getGroupp();
        if (post.getClassRoom() != null && post.getGroupp() == null) {
            if (post.getUser().getId().equals(user.getId())) {
                user.getPosts().remove(post);
                userRepository.save(user);

                classRoom.getPosts().remove(post);
                classRoomRepository.save(classRoom);

                post.getComments().clear();
                postRepository.delete(post);

                return "delete post in class";
            } else {
                throw new NullPointerException("ko phai user nay viet ");
            }
        }

        if (post.getClassRoom() == null && post.getGroupp() != null) {
            if (post.getUser().getId().equals(user.getId())) {
                //STUDENT delete post
                user.getPosts().remove(post);
                userRepository.save(user);

                groupp.getPosts().remove(post);
                grouppRepository.save(groupp);

                post.getComments().clear();
                postRepository.delete(post);
                return "delete post in group";
            } else {
                throw new NullPointerException("ko phai user nay viet ");
            }
        }
        return "delete0";
    }


    public PostDTO1 getPostDetail(Long postId, String token) {
        User user = userRepository.findByToken(token);
        Post post = postRepository.findById(postId);
        PostDTO1 postDTO1 = new PostDTO1(); //return
        if (post.getClassRoom() != null && post.getGroupp() == null) {
            if (post.getClassRoom().getUsers().contains(user)) {
                //TEACHER STUDENT see post detail in  class
                User teacher = post.getUser();
                UserDTO1 teacher1 = new UserDTO1();
                teacher1.setUsername(teacher.getUsername());
                teacher1.setFullName(teacher.getFullName());
                teacher1.setRole(teacher.getRole());
                postDTO1.setUserDTO1(teacher1);

                //lay list comment
                List<Comment> comments = post.getComments();
                List<CommentDTO> commentDTOs = new ArrayList<CommentDTO>();
                for (Comment comment : comments) {
                    CommentDTO commentDTO = new CommentDTO();
                    commentDTO.setCommentContent(comment.getCommentContent());
                    commentDTO.setId(comment.getId());
                    UserDTO1 userDTO1 = new UserDTO1();
                    userDTO1.setUsername(comment.getUser().getUsername());
                    userDTO1.setFullName(comment.getUser().getFullName());
                    userDTO1.setRole(comment.getUser().getRole());
                    commentDTO.setUserDTO1(userDTO1);
                    commentDTOs.add(commentDTO);
                }
                postDTO1.setCommentDTOs(commentDTOs);
                postDTO1.setPostContent(post.getPostContent());
                postDTO1.setPostId(post.getId());

            } else {
                throw new NullPointerException("ko trong class ");
            }

        }

        if (post.getClassRoom() == null && post.getGroupp() != null) {
            if (post.getGroupp().getUsers().contains(user)) {
                //STUDENT see post in group
                User student = post.getUser();
                UserDTO1 student1 = new UserDTO1();
                student1.setUsername(student.getUsername());
                student1.setFullName(student.getFullName());
                student1.setRole(student.getRole());
                postDTO1.setUserDTO1(student1);

                //lay list comment
                List<Comment> comments = post.getComments();
                List<CommentDTO> commentDTOs = new ArrayList<CommentDTO>();
                for (Comment comment : comments) {
                    CommentDTO commentDTO = new CommentDTO();
                    commentDTO.setCommentContent(comment.getCommentContent());
                    commentDTO.setId(comment.getId());
                    UserDTO1 userDTO1 = new UserDTO1();
                    userDTO1.setUsername(comment.getUser().getUsername());
                    userDTO1.setFullName(comment.getUser().getFullName());
                    userDTO1.setRole(comment.getUser().getRole());
                    commentDTO.setUserDTO1(userDTO1);
                    commentDTOs.add(commentDTO);
                }
                postDTO1.setCommentDTOs(commentDTOs);
                postDTO1.setPostContent(post.getPostContent());
                postDTO1.setPostId(post.getId());
            } else {
                throw new NullPointerException("koo trong group ");
            }
        }
        return postDTO1;
    }
}
