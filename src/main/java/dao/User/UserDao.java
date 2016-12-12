package dao.User;
import dto.UserDto;
import entity.Contract;
import entity.Info;
import entity.User;

import java.util.List;

/**
 * Created by vladkvn on 06.12.2016.
 */
public interface UserDao {
    public List<Contract> getContractsUser(UserDto userDto);
    public boolean isExist(String login, String pass);
    public void addContractToUser(User user, Contract contract);
    public User byLoginAndPass(String login, String pass);
    public User addUser(User user);
    public User getUserById(int id);
    public Info getInfoByLogin(String login);
    public User getUserByLogin(String login);
    public void deleteUser(User user);
    public int getIdByLogin(String login);
    public void updateUser(User user);
    public List<User> getAll();
    boolean loginExist(String login);
}
