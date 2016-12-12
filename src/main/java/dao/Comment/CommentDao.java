package dao.Comment;

import entity.Comment;
import entity.Contract;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;

/**
 * Created by vladkvn on 09.12.2016.
 */
public interface CommentDao {
    public void addComment(Comment comment, Contract contract);
    public void deleteCommentById(int id);
    public Comment getCommentById(int id);
    void save(Comment comment);
    List<Comment> getCommentToContract(int contractId);
    public void deleteCommentByContractId(int contractId);
}
