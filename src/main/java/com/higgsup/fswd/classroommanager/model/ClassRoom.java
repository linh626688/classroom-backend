package com.higgsup.fswd.classroommanager.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ClassRoom extends HypermediaLinks {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String className;

    @ManyToMany
    @JoinTable(name = "classroom_has_users",
            joinColumns = {
                    @JoinColumn(name = "class_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            }
    )
    private List<User> users = new ArrayList<User>();

    public User getUser() {
        return user;
    }

    public List<Groupp> getGroupps() {
        return groupps;
    }

    public void setGroupps(List<Groupp> groupps) {
        this.groupps = groupps;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "classroom_has_groupps",
            joinColumns = {
                    @JoinColumn(name = "class_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "groupp_id", referencedColumnName = "id")
            }
    )
    private List<Groupp> groupps = new ArrayList<Groupp>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinTable(name = "classroom_has_posts",
            inverseJoinColumns = {
                    @JoinColumn(name = "class_id", referencedColumnName = "id")
            },
            joinColumns = {
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

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
