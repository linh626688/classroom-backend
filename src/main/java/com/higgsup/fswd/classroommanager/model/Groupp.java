package com.higgsup.fswd.classroommanager.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Long on 5/13/2016.
 */
@Entity
public class Groupp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String groupName;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "class_id")
    private ClassRoom classRoom;
    @ManyToMany
    @JoinTable(name = "groupp_has_user",
            joinColumns = {
                    @JoinColumn(name = "group_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {

                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            }

    )
    private List<User> users = new ArrayList<User>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "group_has_posts",
            joinColumns = {
                    @JoinColumn(name = "group_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "post_id", referencedColumnName = "id")
            }
    )
    private List<Post> posts = new ArrayList<Post>();

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }


}
