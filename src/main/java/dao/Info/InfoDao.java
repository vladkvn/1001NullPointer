package dao.Info;
import entity.Info;

    public interface InfoDao {
    public Info getInfoByLogin(String login);
    public void updateInfo(String login, Info info);
}
