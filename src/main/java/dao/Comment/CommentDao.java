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
    void addComment(Comment comment, Contract contract);
    void deleteCommentById(int id);
    Comment getCommentById(int id);
    void save(Comment comment);
    List<Comment> getCommentToContract(int contractId);
    void deleteCommentByContractId(int contractId);
    boolean CommentExist(int commentId);
    List<Comment>  getComments(int id);
}
