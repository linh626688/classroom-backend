package com.higgsup.fswd.classroommanager.repository;

import com.higgsup.fswd.classroommanager.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by MinhTu on 5/14/2016.
 */
@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    Post findById(Long postId);
}
