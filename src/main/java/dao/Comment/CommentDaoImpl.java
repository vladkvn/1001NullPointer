package dao.Comment;

import entity.Comment;
import entity.Contract;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vladkvn on 09.12.2016.
 */
@Transactional
@Component
public class CommentDaoImpl implements CommentDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public CommentDaoImpl() {
    }

    @Override
    public void addComment(Comment comment, Contract contract) {
        contract.getCommentList().add(comment);
        hibernateTemplate.update(contract);
    }

    @Override
    public void save(Comment comment) {
        hibernateTemplate.save(comment);
    }

    @Override
    public void deleteCommentById(int id) {
        hibernateTemplate.delete(getCommentById(id));
    }

    @Override
    public void deleteCommentByContractId(int contractId) {
        String hql = "FROM entity.Comment where contract_id=:contract_id";
        String params[] = new String[]{"contract_id"};
        String values[] = new String[]{((Integer) contractId).toString()};
        hibernateTemplate.deleteAll(hibernateTemplate.findByNamedParam(hql, params, values));
    }


    @Override
    public Comment getCommentById(int id) {
        return hibernateTemplate.get(Comment.class, id);
    }

    @Override
    public List<Comment> getCommentToContract(int contractId) {
        String hql = "FROM  entity.Comment where contract_id=:contract_id";
        String params[] = new String[]{"contract_id"};
        String values[] = new String[]{((Integer) contractId).toString()};
        return (List<Comment>) hibernateTemplate.findByNamedParam(hql, params, values);
    }

    @Override
    public boolean CommentExist(int commentId) {
        return hibernateTemplate.get(Comment.class,commentId)==null?false:true;
    }

    @Override
    public List<Comment> getComments(int id) {
        return null;
    }
}
