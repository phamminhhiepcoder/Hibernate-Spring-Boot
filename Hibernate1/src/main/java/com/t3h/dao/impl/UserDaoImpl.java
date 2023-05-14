package com.t3h.dao.impl;

import com.t3h.dao.IUserDao;
import com.t3h.entity.User;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements IUserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        super(User.class, sessionFactory);
    }
    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        Query query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void saveOrUpdate(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.saveOrUpdate(user);
            transaction.commit();
        }catch (Exception e) {
            transaction.rollback();
            session.close();
        }finally {
            session.close();
        }
    }
    @Override
    public User findUserById(int id) {
        Session session = sessionFactory.openSession();
         return session.get(User.class, id);
    }

    @Override
    public void delete(int id) {
        try {
            deleteById(id);
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
