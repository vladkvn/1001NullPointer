package dto;

/**
 * Created by vladkvn on 06.12.2016.
 */
public class UserDto {

    protected String login;
    protected String pass;

    public UserDto(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public UserDto() {
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

    public void setPass(String pass) {
        this.pass = pass;
    }
}