package dao.Contract;

import dto.UserDto;
import entity.Comment;
import entity.Company;
import entity.Contract;
import entity.User;

import java.util.List;

/**
 * Created by vladkvn on 09.12.2016.
 */
public interface ContractDao {

    public List<Contract> getAllContracts();
    public List<User> getUsersById(int id);
    public void addContract(Contract contract);
    public Contract getContractById(int id);
    public void deleteContractById(int id);
    public Contract getContractByDiscription(String discription);
    public int getIdByDiscription(String discription);
    public List<User> notInTeam(int id);
    public void update(Contract contract);
    public void addUserToContract(User user, Contract contract);
    public void deleteUserFromContract(User user, Contract contract);
    public List<Comment> getCommentsInContract(Contract contract);
    public boolean isExist(String discription);
    public boolean isExist(int contractId);
    List<Contract> getAllForCompany(int CompanyId);
}
