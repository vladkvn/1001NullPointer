package service.Contract;

import dto.UserDto;
import entity.Company;
import entity.Contract;
import entity.User;

import java.util.List;

/**
 * Created by vladkvn on 13.12.2016.
 */
public interface ContractService {
    public List<User> getUsers(int contractId);
    public List<Contract> getAllContracts();
    public void deleteUserFromContract(UserDto userdto, Contract contract);
    public boolean isContractExist(int contractId);
    public void deleteContract(int id);
    public void updateContract(Contract contract);
    public int getContractId(Contract contract);
    public List<Contract> getContracts(UserDto userDto);
    public Contract getContractById(int id);
    public void createContract(Contract contract, UserDto userDto);
    public List<User> notInTeam(int contractId);
    public void setNewCompany(int contractId, Company company);
}
