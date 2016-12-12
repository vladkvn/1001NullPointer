package entity;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

/**
 * Created by vladkvn on 08.12.2016.
 */
@Entity
@Table(name= "comment")
@Transactional
public class Comment {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    protected Contract contract;

    @OneToOne
    @JoinColumn(name = "user_id")
    protected User user;

    @Column(name = "text")
    protected String text;

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
