package dao.Info;

import dao.User.UserDao;
import entity.Info;
import entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by vladkvn on 06.12.2016.
 */
@Transactional
@Component
public class InfoDaoImpl implements InfoDao {
    public InfoDaoImpl() {
    }

    @Autowired

    private HibernateTemplate hibernateTemplate;

    @Autowired
    UserDao userDao;

    @Override
    public Info getInfoByLogin(String login) {
        return userDao.getUserByLogin(login)==null?null:userDao.getUserByLogin(login).getInfo();
    }

    @Override
    public void updateInfo(String login, Info info) {
        User user = userDao.getUserByLogin(login);
        user.getInfo().setAnother(info);
        hibernateTemplate.update(user);
    }
}
