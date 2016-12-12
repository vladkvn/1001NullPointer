package entity;

import entity.Info;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vladkvn on 12.11.2016.
 */
@Entity
@Table(name = "users")
@Transactional
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @Column(name = "role_name")
    String roleName = "user";

    @Column(name = "login",unique = true, nullable = false)
    protected String login;

    @Column(name = "pass", nullable = false)
    protected String pass;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Info info;


    public List<Contract> getUserContracts() {
        return userContracts;
    }

    @ManyToMany(targetEntity = Contract.class)
    @JoinTable(name = "contract_team",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "contract_id")})
    protected List<Contract> userContracts = new ArrayList<Contract>();



    public Info getInfo() {
        return info;
    }


    public void setUserContracts(List<Contract> userContracts) {
        this.userContracts = userContracts;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public User(String login)
    {
        this.login = login;
    }

    public User(int id, String login) {
        this.id=id;
        this.login=login;
    }

    public User() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    @Override
    public boolean equals(Object obj) {
        if(((User)obj).getLogin().equals(this.login)) {
            return true;
        }
        else {
            return false;
        }
    }

    public User(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
