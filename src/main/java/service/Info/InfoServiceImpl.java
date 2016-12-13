package service.Info;

import dao.Info.InfoDao;
import dao.User.UserDao;
import dto.UserDto;
import entity.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vladkvn on 13.12.2016.
 */
@Component
public class InfoServiceImpl implements InfoService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private InfoDao infoDao;

    @Override
    public Map<String, Object> info(UserDto userDto) {
            Map<String,Object> model = new HashMap<String, Object>();
            if(userDao.loginExist(userDto.getLogin())) {
                model.put("info",userDao.getInfoByLogin(userDto.getLogin()));
            }
            else {
                model.put("error","ошибка");
            }
            return model;
        }

    @Override
    public void updateInfo(UserDto userdto, Info info)
    {
        infoDao.updateInfo(userdto.getLogin(),info);
    }

    @Override
    public Info getInfo(UserDto userdto)
    {
        return infoDao.getInfoByLogin(userdto.getLogin());
    }

    @Override
    public Info getInfo(int id)
    {
        return infoDao.getInfoByLogin(userDao.getUserById(id).getLogin());
    }
}
