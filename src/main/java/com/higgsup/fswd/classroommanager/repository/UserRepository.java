package com.higgsup.fswd.classroommanager.repository;

import com.higgsup.fswd.classroommanager.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tu on 4/24/2016.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);

    User findByToken(String token);

    User findById(Long id);

//    List<User> findByClassroom(ClassRoom classRoom);//hic
}
