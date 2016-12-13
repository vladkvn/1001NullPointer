package service;

import dao.Comment.CommentDao;
import dao.Company.CompanyDao;
import dao.Contract.ContractDao;
import dao.Info.InfoDao;
import dao.User.UserDao;
import dto.UserDto;
import entity.*;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by vladkvn on 07.12.2016.
 */
@Component
public class Service {
    @Autowired
    InfoDao infoDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ContractDao contractDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    CompanyDao companyDao;
    public boolean canRegistr(UserDto userdto) {
        if(userDao.loginExist(userdto.getLogin())) {
            return false;
        }
        else {
            return true;
        }
    }

    public Map<String,Object> info(UserDto userDto){
        Map<String,Object> model = new HashMap<String, Object>();
        if(userDao.loginExist(userDto.getLogin())) {
           model.put("info",userDao.getInfoByLogin(userDto.getLogin()));
        }
        else {
            model.put("error","ошибка");
        }
        return model;
    }

    public boolean auth(UserDto userdto)
    {
        return userDao.isExist(userdto.getLogin(), userdto.getPass());
    }

    public void updateInfo(UserDto userdto, Info info)
    {
        infoDao.updateInfo(userdto.getLogin(),info);
    }

    public int getUserId(UserDto userdto) {
        return userDao.getIdByLogin(userdto.getLogin());
    }

    public Info getInfo(UserDto userdto)
    {
        return infoDao.getInfoByLogin(userdto.getLogin());
    }

    public Info getInfo(int id)
    {
        return infoDao.getInfoByLogin(userDao.getUserById(id).getLogin());
    }

    public boolean idExist(int id)
    {
        return userDao.getUserById(id)==null?false:true;
    }

    public void createContract(Contract contract, UserDto userDto){
        contractDao.addContract(contract);
    }

    public Contract getContractById(int id)
    {
        return contractDao.getContractById(id);
    }

    public List<Contract> getContracts(UserDto userDto) {
            return userDao.getContractsUser(userDto);
    }

    public List<Contract> getAllContracts(){
            return contractDao.getAllContracts();
    }

    public boolean addContractToUser(UserDto userDto, Contract contract) {
        if(!contractDao.isExist(contract.getDiscription())) {
            contractDao.addContract(contract);
            userDao.addContractToUser(userDao.getUserByLogin(userDto.getLogin()),
                    contractDao.getContractByDiscription(contract.getDiscription()));
            return true;
        }
        else return false;
    }


    public List<User> notInTeam(int contractId)
    {
        List<User> list = new ArrayList<User>();
        List<User> list2 = new ArrayList<User>();
        list2 = contractDao.getContractById(contractId).getUsersList();
        list= userDao.getAll();
        list.removeAll(list2);
        return list;
    }

    public int getContractId(Contract contract)
    {
        return contractDao.getIdByDiscription(contract.getDiscription());
    }

    public void updateContract(Contract contract) {
        contractDao.update(contract);
    }

    public void addUserToContract(UserDto userdto, int contractId) {
        Contract contract = contractDao.getContractById(contractId);
        contractDao.addUserToContract(userDao.getUserByLogin(userdto.getLogin()),
                contractDao.getContractByDiscription(contract.getDiscription()));
    }

    public void deleteUserFromContract(UserDto userdto, Contract contract) {
        contractDao.deleteUserFromContract(userDao.getUserByLogin(userdto.getLogin()),
                contractDao.getContractByDiscription(contract.getDiscription()));
    }

    public List<User> getUsers(int contractId) {
        List<User> list;
        list = contractDao.getContractById(contractId).getUsersList();
        return list;
    }

    public void addCommentToContract(Comment comment, int contractId, UserDto userDto) {
        comment.setUser(userDao.getUserByLogin(userDto.getLogin()));
        comment.setContract(contractDao.getContractById(contractId));
        commentDao.save(comment);
    }

    public List<Comment> getComments(int contractId) {
        //return commentDao.getCommentToContract(id);
        for (int i = 0; i < commentDao.getCommentToContract(contractId).size(); i++) {
            System.out.println(commentDao.getCommentToContract(contractId).get(i).getText());
        }
        return commentDao.getCommentToContract(contractId);
    }

    public void deleteComment(int id) {
        commentDao.deleteCommentById(id);
    }

    public String getUserRole(UserDto userdto) {
        return userDao.getUserByLogin(userdto.getLogin()).getRoleName();
    }

    public boolean UserGetContract(UserDto userDto, int id) {
        return contractDao.getContractById(id).getCommentList().contains(new User(userDto.getLogin()));
    }

    public void deleteContract(int id) {
        commentDao.deleteCommentByContractId(id);
        Contract contract = getContractById(id);
        for (int i = 0; i <contract.getUsersList().size() ; i++) {
            contractDao.deleteUserFromContract(contract.getUsersList().get(i),
                    contractDao.getContractById(id));
        }
        contractDao.deleteContractById(id);
    }

    public List<Integer> getGraphicData()
    {
        List<Contract> list = contractDao.getAllContracts();
        List<Integer> resultList = new ArrayList<Integer>();
        Integer kol = 0;
        for (int i = 0; i < list.size(); i++) {
            kol+=getComments(list.get(i).getId()).size();
            System.out.println(kol);
            resultList.add(i,kol);
        }
        return resultList;
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public List<User> getAllUsers()
    {
        return userDao.getAll();
    }

    public void changeRole(UserDto userDto)
    {
        User user = userDao.getUserByLogin(userDto.getLogin());
        if(user.getRoleName().equals("admin")){
            user.setRoleName("user");
        }
        else {
            user.setRoleName("admin");
        }

        userDao.updateUser(user);
    }

    public void setNewCompany(int contractId, Company company) {
        Contract contract = contractDao.getContractById(contractId);
        company = companyDao.getCompanyByName(company.getCompanyName());
        contract.setCompany(company);
        contractDao.update(contract);
    }

    public List<Company> allCompanies() {
        return companyDao.allCompanies();
    }

    public List<Integer> getGraphicData2() {
        List<Company> compamies = companyDao.allCompanies();
        List<Integer> gd = new ArrayList<Integer>();
        int kol=0;
        for (int i = 0; i < compamies.size(); i++) {
            kol = contractDao.getAllForCompany(compamies.get(i).getCompanyId()).size();
            System.out.println("Количество у комании : "+kol);
            gd.add(i, kol);
        }
        return gd;
    }

    public List<String> getGraphic2Legend() {
        List<Company> compamies = companyDao.allCompanies();
        List<String> legend = new ArrayList<String>();
        for (int i = 0; i < compamies.size(); i++) {
            legend.add(i, compamies.get(i).getCompanyName());
        }
        return legend;
    }


    public Company viewCompany(int companyId) {
        return companyDao.getCompanyById(companyId);
    }

    public List<Contract> contractsCompany(int companyId)
    {
        return contractDao.getAllForCompany(companyId);
    }

    public void createCompany(Company company) {
        companyDao.save(company);
    }

    public boolean isCommentOwner(UserDto userDto, int contractId) {
        return commentDao.getCommentById(contractId).
                getUser().getLogin().equals(userDto.getLogin());
    }

    public boolean isContractExist(int contractId) {
        return false;
    }

    public boolean isCommentExist(int commentId) {
        return commentDao.CommentExist(commentId);
    }
}
