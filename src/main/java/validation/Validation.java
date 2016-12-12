package validation;

import dto.UserDto;
import entity.Contract;
import org.springframework.stereotype.Component;
import service.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by vladkvn on 06.12.2016.
 */
@Component
public class Validation {
    Service service;
    public boolean HasAccess(String roleName, UserDto userDto, int contractId)
    {
        if(userDto==null)
            return false;
        if(service.UserGetContract(userDto,contractId))
            return true;
        if(roleName=="admin")
            return true;
        return false;
    }

     public boolean isAutorised(UserDto userDto)
    {
        if(userDto==null)
            return false;
        else return true;
    }

    public boolean adminAccess(String roleName, UserDto userDto)
    {
        if(roleName.equals("admin"))
            return true;
        return false;
    }

}
