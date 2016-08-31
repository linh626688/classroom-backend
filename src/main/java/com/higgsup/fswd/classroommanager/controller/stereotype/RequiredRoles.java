package com.higgsup.fswd.classroommanager.controller.stereotype;

import com.higgsup.fswd.classroommanager.model.Role;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by lent on 4/20/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredRoles {
    Role[] value();
}
