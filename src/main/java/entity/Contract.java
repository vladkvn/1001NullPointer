package entity;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by vladkvn on 08.12.2016.
 */
@Entity
@Table(name= "contract")
@Transactional
public class Contract {

    @Id
    @Column(name = "contract_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @Column(name = "discription", unique = true)
    protected String discription;

    @ManyToMany(mappedBy = "userContracts", cascade = CascadeType.DETACH)
    protected List<User> usersList = new ArrayList<User>();

    @OneToMany
    @JoinTable(name = "contracts_comment",
            joinColumns = {@JoinColumn(name = "contract_id")},
            inverseJoinColumns = {@JoinColumn(name = "comment_id")})
    protected List<Comment> commentList = new ArrayList<Comment>();

    public Contract(int id, String discription, String fullDiscription) {
        this.id=id;
        this.discription=discription;
        this.fullDiscription=fullDiscription;
    }


    public String getFullDiscription() {
        return fullDiscription;
    }

    public void setFullDiscription(String fullDiscription) {
        this.fullDiscription = fullDiscription;
    }

    protected String fullDiscription="";

    public Contract() {
    }

    @Override
    public boolean equals(Object obj) {
        if (((Contract) obj).getId() == this.id) {
            return true;
        } else {
            return false;
        }
    }

    public Contract(int id, String discription) {
        this.discription = discription;
        this.id = id;
    }

    public String getDiscription() {

        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<Comment> commentList) {
        this.commentList = commentList;
    }


    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}

