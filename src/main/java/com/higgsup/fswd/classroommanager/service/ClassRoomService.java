package com.higgsup.fswd.classroommanager.service;

import com.higgsup.fswd.classroommanager.controller.dto.ClassRoomDTO;
import com.higgsup.fswd.classroommanager.controller.dto.ClassRoomDTO1;
import com.higgsup.fswd.classroommanager.controller.dto.UserDTO1;
import com.higgsup.fswd.classroommanager.model.ClassRoom;
import com.higgsup.fswd.classroommanager.model.User;
import com.higgsup.fswd.classroommanager.repository.ClassRoomRepository;
import com.higgsup.fswd.classroommanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MinhTu on 5/9/2016.
 */
@Service
public class ClassRoomService {
    @Autowired
    private ClassRoomRepository classRoomRepository;
    @Autowired
    private UserRepository userRepository;

    public ClassRoomDTO createClassRoom(ClassRoomDTO classRoomDTO, String token) {

        User user = userRepository.findByToken(token);

        ClassRoom classRoom = new ClassRoom();
        classRoom.setUser(user);
        classRoom.setClassName(classRoomDTO.getClassName());

        classRoom.getUsers().add(user);
        classRoom = classRoomRepository.save(classRoom);
        classRoomDTO.setClassId(classRoom.getId());
        classRoomDTO.setClassName(classRoom.getClassName());

        UserDTO1 userDTO1 = new UserDTO1();
        userDTO1.setUsername(user.getUsername());
        userDTO1.setFullName(user.getFullName());
        userDTO1.setRole(user.getRole());
        classRoomDTO.setUserDTO1(userDTO1);
        return classRoomDTO;
    }


    public List<ClassRoomDTO> findClassRooms() {
        List<ClassRoomDTO> classRoomDTOs = new ArrayList<ClassRoomDTO>();

        List<ClassRoom> classRooms = (List<ClassRoom>) classRoomRepository.findAll();
        for (ClassRoom classRoom : classRooms) {
            User user = classRoom.getUser();

            ClassRoomDTO classRoomDTO = new ClassRoomDTO();

            UserDTO1 userDTO1 = new UserDTO1();
            userDTO1.setFullName(user.getFullName());
            userDTO1.setRole(user.getRole());
            userDTO1.setUsername(user.getUsername());
            classRoomDTO.setUserDTO1(userDTO1);
            classRoomDTO.setClassName(classRoom.getClassName());
            classRoomDTO.setClassId(classRoom.getId());

            classRoomDTOs.add(classRoomDTO);

        }
        return classRoomDTOs;

    }

    public List<ClassRoomDTO> getClassRoomOfTeacher(String token) {
        User user = userRepository.findByToken(token);
        List<ClassRoom> classRooms = (List<ClassRoom>) classRoomRepository.findByUser(user);
        List<ClassRoomDTO> classRoomDTOs = new ArrayList<ClassRoomDTO>();
        for (ClassRoom classRoom : classRooms) {
            ClassRoomDTO classRoomDTO = new ClassRoomDTO();
            classRoomDTO.setClassName(classRoom.getClassName());
            classRoomDTO.setClassId(classRoom.getId());
            classRoomDTOs.add(classRoomDTO);
        }
        return classRoomDTOs;

    }

    public String addUserToClassRoom(Long id, String token) {
        User user = userRepository.findByToken(token);
        ClassRoom classRoom = classRoomRepository.findById(id);
        List<ClassRoom> classRooms = user.getClassRooms();

        if (!classRooms.contains(classRoom)) {
            classRoom.getUsers().add(user);
            user.getClassRooms().add(classRoom);
            classRoom = classRoomRepository.save(classRoom);

            return classRoom.getClassName();
        } else {
            throw new NullPointerException("da o trong class");
        }

    }

    public String deleteClassRoom(Long id, String token) {
        User user = userRepository.findByToken(token);
        ClassRoom classRoom = classRoomRepository.findById(id);

        if (classRoom.getUser() == user) {
            user.getClassRooms().remove(classRoom);
            userRepository.save(user);

            classRoom.getPosts().clear();

            classRoom.getGroupps().clear();

            classRoomRepository.delete(classRoom);
            return "delete";
        } else {
            throw new NullPointerException("ko phai teacher cua class");
        }

    }

    public List<ClassRoomDTO> getClassRoomOfStudent(String token) {
        User user = userRepository.findByToken(token);
        List<ClassRoom> classRooms = classRoomRepository.findByUser(user);

        List<ClassRoomDTO> classRoomDTOs = new ArrayList<ClassRoomDTO>();
        for (ClassRoom classRoom : classRooms) {
            ClassRoomDTO classRoomDTO = new ClassRoomDTO();
            classRoomDTO.setClassName(classRoom.getClassName());
            classRoomDTOs.add(classRoomDTO);
        }
        return classRoomDTOs;
    }

    public ClassRoomDTO1 getClassDetail(Long id) {
        ClassRoom classRoom = classRoomRepository.findById(id);
        User user = classRoom.getUser();
        UserDTO1 userDTO1 = new UserDTO1();

        userDTO1.setUsername(user.getUsername());
        userDTO1.setFullName(user.getFullName());
        userDTO1.setRole(user.getRole());

        List<User> users = classRoom.getUsers();
        List<UserDTO1> userDTO1s = new ArrayList<UserDTO1>();
        for (User user1 : users) {
            UserDTO1 userDTO111 = new UserDTO1();
            userDTO111.setUsername(user1.getUsername());
            userDTO111.setFullName(user1.getFullName());
            userDTO111.setRole(user1.getRole());
            userDTO1s.add(userDTO111);
        }
        ClassRoomDTO1 classRoomDTO1 = new ClassRoomDTO1();
        classRoomDTO1.setClassId(classRoom.getId());
        classRoomDTO1.setClassName(classRoom.getClassName());
        classRoomDTO1.setUserDTO1(userDTO1);
        classRoomDTO1.setUserDTO1s(userDTO1s);

        return classRoomDTO1;

    }

    public String deleteStudent(Long id, String token) {
        User user = userRepository.findByToken(token);
        ClassRoom classRoom = classRoomRepository.findById(id);
        if (classRoom.getUsers().contains(user)) {
            classRoom.getUsers().remove(user);
            user.getClassRooms().remove(classRoom);
            classRoom = classRoomRepository.save(classRoom);
            user = userRepository.save(user);
            return "clear";
        } else {
            throw new NullPointerException("ko o trong class");
        }

    }

    public ClassRoomDTO editClassRoom(Long id, ClassRoomDTO classRoomDTO, String token) {
        User user = userRepository.findByToken(token);
        ClassRoom classRoom = classRoomRepository.findById(id);
        ClassRoomDTO classRoomDTO1 = new ClassRoomDTO();
        if (classRoom.getUser() == user) {
            classRoom.setClassName(classRoomDTO.getClassName());
            classRoom = classRoomRepository.save(classRoom);
            classRoomDTO1.setClassName(classRoom.getClassName());
            return classRoomDTO;
        } else {
            throw new NullPointerException("ko phai teacher cua class");
        }

    }

    public List<ClassRoomDTO> getNotEnrollClasses(String token) {
        User user = userRepository.findByToken(token);
        List<ClassRoom> classRooms = (List<ClassRoom>) classRoomRepository.findAll();
        List<ClassRoomDTO> classRoomDTOs = new ArrayList<ClassRoomDTO>();
        for (ClassRoom classRoom : classRooms) {
            if (!classRoom.getUsers().contains(user)) {
                ClassRoomDTO classRoomDTO = new ClassRoomDTO();
                classRoomDTO.setClassId(classRoom.getId());
                classRoomDTO.setClassName(classRoom.getClassName());
                UserDTO1 userDTO1 = new UserDTO1();
                userDTO1.setUsername(classRoom.getUser().getUsername());
                classRoomDTO.setUserDTO1(userDTO1);

                classRoomDTOs.add(classRoomDTO);
            }
        }
        return classRoomDTOs;
    }
}
