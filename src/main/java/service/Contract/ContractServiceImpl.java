package service.Contract;

import dao.Comment.CommentDao;
import dao.Company.CompanyDao;
import dao.Contract.ContractDao;
import dao.User.UserDao;
import dto.UserDto;
import entity.Company;
import entity.Contract;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vladkvn on 13.12.2016.
 */
@Component
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractDao contractDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private CompanyDao companyDao;

    @Override
    public List<User> getUsers(int contractId) {
        List<User> list;
        list = contractDao.getContractById(contractId).getUsersList();
        return list;
    }

    @Override
    public List<Contract> getAllContracts(){
        return contractDao.getAllContracts();
    }

    @Override
    public void deleteUserFromContract(UserDto userdto, Contract contract) {
        contractDao.deleteUserFromContract(userDao.getUserByLogin(userdto.getLogin()),
                contractDao.getContractByDiscription(contract.getDiscription()));
    }

    @Override
    public boolean isContractExist(int contractId) {
        return contractDao.isExist(contractId);
    }

    @Override
    public void deleteContract(int id) {
        commentDao.deleteCommentByContractId(id);
        Contract contract = getContractById(id);
        for (int i = 0; i <contract.getUsersList().size() ; i++) {
            contractDao.deleteUserFromContract(contract.getUsersList().get(i),
                    contractDao.getContractById(id));
        }
        contractDao.deleteContractById(id);
    }

    @Override
    public void updateContract(Contract contract) {
        contractDao.update(contract);
    }

    @Override
    public int getContractId(Contract contract)
    {
        return contractDao.getIdByDiscription(contract.getDiscription());
    }

    @Override
    public List<Contract> getContracts(UserDto userDto) {
        return userDao.getContractsUser(userDto);
    }


    @Override
    public Contract getContractById(int id) {
        return contractDao.getContractById(id);
    }

    @Override

    public void createContract(Contract contract, UserDto userDto){
        contractDao.addContract(contract);
    }

    @Override
    public List<User> notInTeam(int contractId)
    {
        List<User> list = new ArrayList<User>();
        List<User> list2 = new ArrayList<User>();
        list2 = contractDao.getContractById(contractId).getUsersList();
        list= userDao.getAll();
        list.removeAll(list2);
        return list;
    }

    @Override
    public void setNewCompany(int contractId, Company company) {
        Contract contract = contractDao.getContractById(contractId);
        company = companyDao.getCompanyByName(company.getCompanyName());
        contract.setCompany(company);
        contractDao.update(contract);
    }
}
