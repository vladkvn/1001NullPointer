package dao.User;

import dto.UserDto;
import entity.Contract;
import entity.Info;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Repository
@Transactional
public class UserDaoImpl implements UserDao{
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public UserDaoImpl() {
    }

    @Override
    public boolean isExist(String login, String pass) {
        String hql = "FROM entity.User WHERE login = :login and pass = :password";
        String params[] = new String[]{"login", "password"};
        String values[] = new String[]{login, pass};
        List<?> list = hibernateTemplate.findByNamedParam(hql, params, values);
        System.out.println(list.size());
        return list.size()==0?false:true;
    }

    @Override
    public boolean loginExist(String login) {
        String hql = "FROM entity.User WHERE login = :login";
        String params[] = new String[]{"login"};
        String values[] = new String[]{login};
        List<?> list = hibernateTemplate.findByNamedParam(hql, params, values);
        return list.size()==0?false:true;
    }

    @Override
    public User byLoginAndPass(String login, String pass) {
        String hql = "FROM  entity.User WHERE login = :login and pass = :password";
        String params[] = new String[]{"login", "password"};
        String values[] = new String[]{login, pass};
        List<?> list = hibernateTemplate.findByNamedParam(hql, params, values);
        return (User)list.get(0);
    }

    @Override
    public void deleteUser(User user) {
        hibernateTemplate.delete(user);
    }

    @Override
    public void addContractToUser(User user, Contract contract){
        user = getUserByLogin(user.getLogin());
        user.getUserContracts().add(contract);
        hibernateTemplate.update(user);
    }

    @Override
    public User addUser(User user) {
        user.setInfo(new Info());
        hibernateTemplate.save(user);
        return user;
    }

    @Override
    public User getUserById(int id) {
        return hibernateTemplate.get(User.class,id);
    }

    @Override
    public Info getInfoByLogin(String login) {
        return getUserByLogin(login)==null?
                null:
                getUserByLogin(login).getInfo();
    }

    @Override
    public User getUserByLogin(String login) {
        String hql = "FROM  entity.User WHERE login = :login";
        String params[] = new String[]{"login"};
        String values[] = new String[]{login};
        List<?> list = hibernateTemplate.findByNamedParam(hql, params, values);
        return list.size()!=0?(User)list.get(0):null;
    }

    @Override
    public int getIdByLogin(String login) {
        return getUserByLogin(login).getId();
    }

    @Override
    public List<Contract> getContractsUser(UserDto userDto) {
        return getUserByLogin(userDto.getLogin()).getUserContracts();
    }

    public void updateUser(User user){
        hibernateTemplate.update(user);
    }

    public List<User> getAll()
    {
        String hql = "FROM  entity.User";
        String params[] = new String[]{};
        String values[] = new String[]{};
        return (List<User>) hibernateTemplate.findByNamedParam(hql, params, values);
    }
}
