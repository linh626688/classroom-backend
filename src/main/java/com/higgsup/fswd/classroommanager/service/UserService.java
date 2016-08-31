package com.higgsup.fswd.classroommanager.service;

import com.higgsup.fswd.classroommanager.controller.dto.ClassRoomDTO;
import com.higgsup.fswd.classroommanager.controller.dto.GrouppDTO;
import com.higgsup.fswd.classroommanager.controller.dto.UserDTO;
import com.higgsup.fswd.classroommanager.controller.dto.UserDTO1;
import com.higgsup.fswd.classroommanager.model.ClassRoom;
import com.higgsup.fswd.classroommanager.model.Groupp;
import com.higgsup.fswd.classroommanager.model.User;
import com.higgsup.fswd.classroommanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Tu on 4/24/2016.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDTO userDTO) {
        ///User user = new User();
        User user1 = userRepository.findByUsername(userDTO.getUsername());
        if (user1 == null) {
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setFullName(userDTO.getFullName());
            user.setPassword(userDTO.getPassword());
            user.setRole(userDTO.getRole());
            return userRepository.save(user);
        } else {
            throw new NullPointerException("username da ton tai!");
        }

    }

    public User findUserToken(String token) {
        return userRepository.findByToken(token);
    }


    public UserDTO doLogin(UserDTO userDTO) {
        // 1. Generate token if not exist
        // 2. Set expired time for token
        User user = userRepository.findByUsername(userDTO.getUsername());

        if (userDTO.getPassword().equals(user.getPassword())) {
            if (user.getToken() == null) {
                user.setToken(UUID.randomUUID().toString());
                user.setTokenExpiry(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)));
            } else {
                user.setTokenExpiry(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)));
            }
            user = userRepository.save(user);
            UserDTO result = new UserDTO();
            result.setUsername(user.getUsername());
            result.setFullName(user.getFullName());
            result.setRole(user.getRole());
            result.setToken(user.getToken());
            return result;
        } else {
            throw new NullPointerException("sai username hoac password");
        }
    }

    public UserDTO findUser(String token) {
        User user = userRepository.findByToken(token);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setFullName(user.getFullName());
        userDTO.setRole(user.getRole());
        List<ClassRoom> classRooms = user.getClassRooms();
        List<ClassRoomDTO> classRoomDTOs = new ArrayList<ClassRoomDTO>();
        List<Groupp> groupps = user.getGroupps();
        List<GrouppDTO> grouppDTOs = new ArrayList<GrouppDTO>();

        for (ClassRoom classRoom : classRooms) {
            ClassRoomDTO classRoomDTO = new ClassRoomDTO();
            classRoomDTO.setClassId(classRoom.getId());
            classRoomDTO.setClassName(classRoom.getClassName());
            UserDTO1 userDTO1 = new UserDTO1();
            userDTO1.setUsername(classRoom.getUser().getUsername());
            classRoomDTO.setUserDTO1(userDTO1);
            classRoomDTOs.add(classRoomDTO);
        }
        for (Groupp groupp: groupps){
            GrouppDTO grouppDTO = new GrouppDTO();
            grouppDTO.setGroupId(groupp.getId());
            grouppDTO.setGroupName(groupp.getGroupName());
            grouppDTOs.add(grouppDTO);
        }
        userDTO.setClassRoomDTOs(classRoomDTOs);
        userDTO.setGrouppDTOs(grouppDTOs);
        return userDTO;
    }

    public void doLogout(String token) {
        User user = userRepository.findByToken(token);
        user.setToken(null);
        userRepository.save(user);
    }
}
