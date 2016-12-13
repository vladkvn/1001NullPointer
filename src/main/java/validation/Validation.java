package validation;

import dto.UserDto;
import entity.Company;
import entity.Contract;
import entity.Info;
import org.springframework.stereotype.Component;
import service.Service;

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

    public boolean UserDtoIsValid(UserDto userDto)
    {
        if(userDto.getLogin().length()>45) {
            return false;
        }
        if(userDto.getPass().length()>45) {
            return false;
        }
        else{
            return true;
        }
    }

    public boolean InfoDtoIsValid(Info info)
    {
        if(info.getAge().length()>45)
            return false;
        if(info.getCity().length()>45)
            return false;
        if(info.getEducation().length()>45)
            return false;
        if(info.getFN().length()>45)
            return false;
        if(info.getLN().length()>45)
            return false;
        if(info.getSex().length()>45)
            return false;
        else return true;
    }

    public boolean CompanyDtoIsValid(Company company)
    {
        if(company.getCompanyName().length()>45||
                company.getCompanyName().length()==0)
        {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean ContractIsValid(Contract contract) {
        if(contract.getDiscription().length()>45)
            return false;
        if(contract.getFullDiscription().length()>2000)
            return false;
        else return true;
    }
}
