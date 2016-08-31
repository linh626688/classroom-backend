package com.higgsup.fswd.classroommanager.repository;

import com.higgsup.fswd.classroommanager.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Long on 5/15/2016.
 */
@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    Comment findById(Long commentId);
}
