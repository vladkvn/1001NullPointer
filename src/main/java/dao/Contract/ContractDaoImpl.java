package dao.Contract;

import dto.UserDto;
import entity.Comment;
import entity.Contract;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.Cipher;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by vladkvn on 09.12.2016.
 */
@Transactional
@Component
public class ContractDaoImpl implements ContractDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;


    public ContractDaoImpl() {
    }

    @Override
    public List<Contract> getAllContracts() {
        String hql = "FROM entity.Contract";
        String params[] = new String[]{};
        String values[] = new String[]{};
        List<Contract> list = (List<Contract>) hibernateTemplate.findByNamedParam(hql, params,values);
        return list;
    }

    @Override
    public List<User> getUsersById(int id) {
        String hql = "FROM  entity.Contract";
        String params[] = new String[]{};
        String values[] = new String[]{};
        List<User> list = (List<User>) hibernateTemplate.findByNamedParam(hql, params, values);
        return list;
    }

    @Override
    public void addContract(Contract contract) {
        hibernateTemplate.save(contract);
    }

    @Override
    public Contract getContractById(int id) {
        return hibernateTemplate.get(Contract.class,id);
    }


    @Override
    public void deleteContractById(int id) {
        hibernateTemplate.delete(getContractById(id));
    }

    @Override
    public Contract getContractByDiscription(String discription) {
        String hql = "FROM entity.Contract where discription= :discription";
        String params[] = new String[]{"discription"};
        String values[] = new String[]{discription};
        List<Contract> list = (List<Contract>) hibernateTemplate.findByNamedParam(hql, params, values);
        return list.size()==0?null:list.get(0);
    }

    @Override
    public boolean isExist(String discription) {
        String hql = "FROM entity.Contract where discription= :discription";
        String params[] = new String[]{"discription"};
        String values[] = new String[]{discription};
        List<Contract> list = (List<Contract>) hibernateTemplate.findByNamedParam(hql, params, values);
        return list.size()==0?false:true;
    }

    @Override
    public int getIdByDiscription(String discription) {
        String hql = "FROM entity.Contract where discription= :discription";
        String params[] = new String[]{"discription"};
        String values[] = new String[]{discription};
        List<Contract> list = (List<Contract>) hibernateTemplate.findByNamedParam(hql, params, values);
        return list.size()==0?null:list.get(0).getId();
    }

    @Override
    public List<User> notInTeam(int id) {
        String hql = "FROM  entity.User";
        String params[] = new String[]{""};
        String values[] = new String[]{""};
        List<User> list = (List<User>) hibernateTemplate.findByNamedParam(hql, params, values);
        list.removeAll(getContractById(id).getUsersList());
        return list;
    }

    public int getContractId(String discription)
    {
        return getIdByDiscription(discription);
    }

    @Override
    public void update(Contract contract) {
        Contract contract1 = getContractById(contract.getId());
        contract1.setDiscription(contract.getDiscription());
        contract1.setFullDiscription(contract.getFullDiscription());
        contract1.setCompany(contract.getCompany());
        hibernateTemplate.update(contract1);
    }

    @Override
    public void addUserToContract(User user, Contract contract) {
        user.getUserContracts().add(contract);
        hibernateTemplate.update(user);
    }

    @Override
    public void deleteUserFromContract(User user, Contract contract) {
        user.getUserContracts().remove(contract);
        hibernateTemplate.update(user);
    }

    @Override
    public List<Comment> getCommentsInContract(Contract contract) {
        return contract.getCommentList();
    }

    @Override
    public List<Contract> getAllForCompany(int companyId) {
        String hql = "FROM entity.Contract where company_id= :company_id";
        String params[] = new String[]{"company_id"};
        String values[] = new String[]{String.valueOf(companyId)};
        List<Contract> list = (List<Contract>) hibernateTemplate.findByNamedParam(hql, params, values);
        return list;
    }

    @Override
    public boolean isExist(int contractId) {
        return hibernateTemplate.get(Contract.class, contractId)==null?false:true;
    }
}
