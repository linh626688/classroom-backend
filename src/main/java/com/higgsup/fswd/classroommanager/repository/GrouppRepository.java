package com.higgsup.fswd.classroommanager.repository;

import com.higgsup.fswd.classroommanager.model.Groupp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Long on 5/13/2016.
 */
@Repository
public interface GrouppRepository extends CrudRepository<Groupp, Long> {
    Groupp findById(Long groupId);

}
