package entity;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

/**
 * Created by vladkvn on 14.11.2016.
 */
@Entity
@Table(name= "info")
@Transactional
public class Info {

    public Info(User user, String city, String sex, String age, String education, String FN, String LN) {
        this.user = user;
        this.city = city;
        this.sex = sex;
        this.age = age;
        this.education = education;
        this.FN = FN;
        this.LN = LN;
    }

    public Info() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name= "LN")
    private String LN;
    @Column(name= "FN")
    private String FN;
    @Column(name= "education")
    private String education;
    @Column(name= "age")
    private String age;
    @Column(name= "sex")
    private String sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLN() {
        return LN;
    }

    public void setLN(String LN) {
        this.LN = LN;
    }

    public String getFN() {
        return FN;
    }

    public void setFN(String FN) {
        this.FN = FN;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAnother(Info info)
    {
        this.age = info.getAge();
        this.city = info.getCity();
        this.education = info.getEducation();
        this.LN = info.getLN();
        this.FN = info.getFN();
        this.sex = info.getSex();
    }

    @Column(name= "city")
    private String city;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User user;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
