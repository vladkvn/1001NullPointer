package com.springapp.mvc;

import org.hibernate.annotations.OnDelete;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

/**
 * Created by vladkvn on 12.11.2016.
 */
@Entity
@Table(name = "users")
@Transactional
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "login",unique = true, nullable = false)
    private String login;

    @Column(name = "pass", nullable = false)
    private String pass;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Info info;

    public Info getInfo() {
        return info;
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

    public User(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
