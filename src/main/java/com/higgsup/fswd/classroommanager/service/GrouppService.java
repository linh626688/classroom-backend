package com.higgsup.fswd.classroommanager.service;

import com.higgsup.fswd.classroommanager.controller.dto.GrouppDTO;
import com.higgsup.fswd.classroommanager.controller.dto.GrouppDTO1;
import com.higgsup.fswd.classroommanager.controller.dto.UserDTO1;
import com.higgsup.fswd.classroommanager.model.ClassRoom;
import com.higgsup.fswd.classroommanager.model.Groupp;
import com.higgsup.fswd.classroommanager.model.User;
import com.higgsup.fswd.classroommanager.repository.ClassRoomRepository;
import com.higgsup.fswd.classroommanager.repository.GrouppRepository;
import com.higgsup.fswd.classroommanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MinhTu on 5/14/2016.
 */
@Service
public class GrouppService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClassRoomRepository classRoomRepository;
    @Autowired
    private GrouppRepository grouppRepository;

    public GrouppDTO createGroupp(Long id, GrouppDTO grouppDTO, String token) {
        User user = userRepository.findByToken(token);
        ClassRoom classRoom = classRoomRepository.findById(id);

        Groupp groupp = new Groupp();
        if (classRoom.getUser() == user) {
            groupp.setGroupName(grouppDTO.getGroupName());
            groupp.setClassRoom(classRoom);
            grouppRepository.save(groupp);

            classRoom.getGroupps().add(groupp);
            classRoomRepository.save(classRoom);
            return grouppDTO;
        } else {
            throw new NullPointerException("ko phai teacher cua class");
        }
    }

    public List<GrouppDTO> getGroupps(Long id, String token) {
        User user = userRepository.findByToken(token);
        ClassRoom classRoom = classRoomRepository.findById(id);

        List<User> users = classRoom.getUsers();
        List<GrouppDTO> grouppDTOs = new ArrayList<GrouppDTO>();
        if (users.contains(user)) {
            List<Groupp> groupps = classRoom.getGroupps();
            for (Groupp groupp : groupps) {
                GrouppDTO grouppDTO = new GrouppDTO();
                grouppDTO.setGroupName(groupp.getGroupName());
                grouppDTO.setGroupId(groupp.getId());
                grouppDTOs.add(grouppDTO);
            }
            return grouppDTOs;
        } else {
            throw new NullPointerException("ko phai thuoc class");
        }
    }

    public String addUserToGroup(String token, Long groupId) {
        User user = userRepository.findByToken(token);
        Groupp groupp = grouppRepository.findById(groupId);

        if (groupp.getClassRoom().getUsers().contains(user)) {
            if (!groupp.getUsers().contains(user)) {
                //add
                groupp.getUsers().add(user);
                groupp = grouppRepository.save(groupp);

                return "add success";
            } else {
                // return "da o trong group"; //throw exception
                throw new NullPointerException("da o trong group");
            }
        } else {
            throw new NullPointerException("ko o trong classroom"); // chay thu xem dc k
            // return "ko o trong classroom" ; //throw exception
        }

    }

    public String deleteUserFromGroup(String token, Long groupId) {
        User user = userRepository.findByToken(token);
        Groupp groupp = grouppRepository.findById(groupId);

        if (groupp.getClassRoom().getUsers().contains(user)) {
            if (groupp.getUsers().contains(user)) {
                //remove
                groupp.getUsers().remove(user);
                groupp = grouppRepository.save(groupp);

                return "remove success";

            } else {
                return "ko o trong group"; //throw exception
            }
        } else {
            return "ko o trong classroom"; //throw exception
        }
    }

    public String deleteGroupp(Long groupId, String token) {
        User user = userRepository.findByToken(token);
        Groupp groupp = grouppRepository.findById(groupId);
        ClassRoom classRoom = groupp.getClassRoom();
        if (groupp.getClassRoom().getUser() == user) {
            //delete
            user.getGroupps().remove(groupp);
            userRepository.save(user);

            groupp.getClassRoom().getGroupps().remove(groupp);
            classRoomRepository.save(classRoom);

            groupp.getPosts().clear();

            grouppRepository.delete(groupp);

            return "delete success";
        } else {
            return "ko xoa dc";//throw exception
        }

    }

    public GrouppDTO1 getGroupDetail(String token, Long groupId) {
        User user = userRepository.findByToken(token);

        Groupp groupp = grouppRepository.findById(groupId);
        GrouppDTO1 grouppDTO1 = new GrouppDTO1();
        if (groupp.getClassRoom().getUsers().contains(user)) {
            //get
            List<UserDTO1> userDTO1s = new ArrayList<UserDTO1>();
            List<User> users = groupp.getUsers();
            for (User user1 : users) {
                UserDTO1 userDTO1 = new UserDTO1();
                userDTO1.setUsername(user1.getUsername());
                userDTO1.setFullName(user1.getFullName());
                userDTO1.setRole(user1.getRole());
                userDTO1s.add(userDTO1);
            }
            grouppDTO1.setGroupName(groupp.getGroupName());
            grouppDTO1.setUserDTO1s(userDTO1s);
            grouppDTO1.setGroupId(groupp.getId());
        } else {
            //ko o trong lop
            return null; //throw exception
        }

        return grouppDTO1;
    }

    public GrouppDTO editGroup(Long groupId, GrouppDTO grouppDTO, String token) {
        User user = userRepository.findByToken(token);

        Groupp groupp = grouppRepository.findById(groupId);
        GrouppDTO grouppDTO1 = new GrouppDTO();

        if (groupp.getClassRoom().getUser() == user) {
            //update
            groupp.setGroupName(grouppDTO.getGroupName());
            groupp = grouppRepository.save(groupp);
            grouppDTO1.setGroupName(groupp.getGroupName());
            grouppDTO1.setGroupId(groupp.getId());

        }
        return grouppDTO1;
    }

    public List<GrouppDTO> getNotEnrollGroup(Long classId, String token) {
        User user = userRepository.findByToken(token);
        ClassRoom classRoom = classRoomRepository.findById(classId);
        List<User> users = classRoom.getUsers();
        List<GrouppDTO> grouppDTOs = new ArrayList<GrouppDTO>();
        if (users.contains(user)) {
            List<Groupp> groupps = classRoom.getGroupps();
            for (Groupp groupp : groupps) {
                if (!groupp.getUsers().contains(user)){
                    GrouppDTO grouppDTO = new GrouppDTO();
                    grouppDTO.setGroupName(groupp.getGroupName());
                    grouppDTO.setGroupId(groupp.getId());
                    grouppDTOs.add(grouppDTO);
                }
            }
            return grouppDTOs;
        } else {
            throw new NullPointerException("ko phai thuoc class");
        }
    }

    public List<GrouppDTO> getEnrollGroup(Long classId, String token) {
        User user = userRepository.findByToken(token);
        ClassRoom classRoom = classRoomRepository.findById(classId);
        List<User> users = classRoom.getUsers();
        List<GrouppDTO> grouppDTOs = new ArrayList<GrouppDTO>();
        if (users.contains(user)) {
            List<Groupp> groupps = classRoom.getGroupps();
            for (Groupp groupp : groupps) {
                if (groupp.getUsers().contains(user)){
                    GrouppDTO grouppDTO = new GrouppDTO();
                    grouppDTO.setGroupName(groupp.getGroupName());
                    grouppDTO.setGroupId(groupp.getId());
                    grouppDTOs.add(grouppDTO);
                }
            }
            return grouppDTOs;
        } else {
            throw new NullPointerException("ko phai thuoc class");
        }

    }
}
