package com.higgsup.fswd.classroommanager.repository;

import com.higgsup.fswd.classroommanager.model.ClassRoom;
import com.higgsup.fswd.classroommanager.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hunghip on 4/27/2016.
 */
@Repository
public interface ClassRoomRepository extends CrudRepository<ClassRoom, Long> {
    ClassRoom findById(Long classId);

    List<ClassRoom> findByUser(User user);


}
