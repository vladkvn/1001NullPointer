package dto;

/**
 * Created by vladkvn on 12.12.2016.
 */
public class UserRegDto extends UserDto {
    String pass2;

    public UserRegDto(String login, String pass, String pass2) {
        this.login = login;
        this.pass = pass;
        this.pass2 = pass2;
    }

    public UserRegDto() {
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }



    public boolean isOk() {
        if(pass==null)
            return false;
        return pass.equals(pass2);
    }
}
