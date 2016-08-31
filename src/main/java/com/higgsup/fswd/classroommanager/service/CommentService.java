package com.higgsup.fswd.classroommanager.service;

import com.higgsup.fswd.classroommanager.controller.dto.CommentDTO;
import com.higgsup.fswd.classroommanager.controller.dto.UserDTO1;
import com.higgsup.fswd.classroommanager.model.Comment;
import com.higgsup.fswd.classroommanager.model.Post;
import com.higgsup.fswd.classroommanager.model.User;
import com.higgsup.fswd.classroommanager.repository.CommentRepository;
import com.higgsup.fswd.classroommanager.repository.PostRepository;
import com.higgsup.fswd.classroommanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Long on 5/15/2016.
 */
@Service
public class CommentService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;


    public CommentDTO createCommentInPost(String token, Long postId, CommentDTO commentDTO) {
        User user = userRepository.findByToken(token);
        Post post = postRepository.findById(postId);
        CommentDTO commentDTOtrave = new CommentDTO();
        if (post.getGroupp() != null && post.getClassRoom() == null) {
            if (post.getGroupp().getUsers().contains(user)) {
                //add comment in group
                Comment comment = new Comment();
                comment.setCommentContent(commentDTO.getCommentContent());
                comment.setUser(user);
                comment.setPost(post);
                comment = commentRepository.save(comment);
                post.getComments().add(comment);
                postRepository.save(post);

                commentDTOtrave.setCommentContent(commentDTO.getCommentContent());
                commentDTOtrave.setId(commentDTO.getId());
                UserDTO1 userDTO1 = new UserDTO1(); //clone thông tin user vào userDTO để khi comment trả về có tên user
                userDTO1.setUsername(user.getUsername());
                userDTO1.setFullName(user.getFullName());
                userDTO1.setRole(user.getRole());
                commentDTOtrave.setUserDTO1(userDTO1);
            } else {
                throw new NullPointerException("koo trong group ");
            }
        }
        if (post.getGroupp() == null && post.getClassRoom() != null) {
            if (post.getClassRoom().getUsers().contains(user)) {
                //add comment in class
                Comment comment = new Comment();
                comment.setCommentContent(commentDTO.getCommentContent());
                comment.setUser(user);
                comment.setPost(post);
                comment = commentRepository.save(comment);
                post.getComments().add(comment);
                postRepository.save(post);

                commentDTOtrave.setCommentContent(commentDTO.getCommentContent());
                commentDTOtrave.setId(commentDTO.getId());

                UserDTO1 userDTO1 = new UserDTO1(); //clone thông tin user vào userDTO để khi comment trả về có tên user
                userDTO1.setUsername(user.getUsername());
                userDTO1.setFullName(user.getFullName());
                userDTO1.setRole(user.getRole());
                commentDTOtrave.setUserDTO1(userDTO1);

            } else {
                throw new NullPointerException("koo trong class ");
            }
        }
        if (post.getGroupp() == null && post.getClassRoom() == null) {
            //ko dc comment
            CommentDTO commentabc = new CommentDTO();
            commentabc.setCommentContent("Khong duoc");
            return commentabc;

        }
        return commentDTOtrave;
    }

    //LONG

    public CommentDTO editCommentInPost(String token, Long commentId, CommentDTO commentDTO) {
        User user = userRepository.findByToken(token);
        Comment comment = commentRepository.findById(commentId);

        CommentDTO commentDTO1 = new CommentDTO();

        if (comment.getUser() == user) {
            comment.setCommentContent(commentDTO.getCommentContent());
            comment = commentRepository.save(comment);
            commentDTO1.setCommentContent(comment.getCommentContent());
            commentDTO1.setId(comment.getId());
            return commentDTO1;
        } else {
            throw new NullPointerException("koo phai nguoi viet cmt nay ");
        }
    }

    //LONG

    public String deleteComment(Long commentId, String token) {
        User user = userRepository.findByToken(token);
        Comment comment = commentRepository.findById(commentId);
        Post post = comment.getPost();
        if (comment.getUser() == user) {
            post.getComments().remove(comment);
            postRepository.save(post);

            commentRepository.delete(comment);
            return "Đã xóa comment viết bởi " + user.getUsername();
        } else {
            throw new NullPointerException("koo phai nguoi viet cmt nay ");
        }

    }
}
