package com.higgsup.fswd.classroommanager.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MinhTu on 5/14/2016.
 */
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String postContent;


    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassRoom classRoom;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Groupp groupp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "post_has_comments",
            joinColumns = {
                    @JoinColumn(name = "post_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "comment_id", referencedColumnName = "id")
            }
    )
    private List<Comment> comments = new ArrayList<Comment>();

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
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

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public Groupp getGroupp() {
        return groupp;
    }

    public void setGroupp(Groupp groupp) {
        this.groupp = groupp;
    }
}
