package com.t3h.dao.impl;

import com.t3h.dao.GenericDao;
import com.t3h.entity.BaseEntity;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Queue;

public class GenericDaoImpl<T extends BaseEntity> implements GenericDao<T> {


    protected Class<T> entityClass;

    private SessionFactory sessionFactory;

    public GenericDaoImpl(final Class<T> entityClass, SessionFactory sessionFactory) {
        this.entityClass = entityClass;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public T findById(Integer id) {
        return this.sessionFactory.openSession().get(this.entityClass, id);
    }

    @Override
    public List<T> findAll() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.entityClass);
        Root<T> root = criteriaQuery.from(this.entityClass);
        criteriaQuery.select(root);
        Query query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void create(T entity) {
        System.out.println("Start create entity");
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
            System.out.println("Create entity successfully");
        }catch(Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            transaction.rollback();
            session.close();
        }finally {
            transaction.rollback();
            session.close();
        }
    }

    @Override
    public T update(T entity) {
        System.out.println("start update entity");

        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        T entityUpdated = null;

        try {
            entityUpdated = (T) session.merge(entity);
            transaction.commit();
        }catch(Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            transaction.rollback();
            session.close();
        }finally {
            transaction.rollback();
            session.close();
        }
        return entityUpdated;
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        T entityDelete = this.sessionFactory.openSession().get(this.entityClass, id);
        if(entityDelete == null) {
            throw new SQLException(String.format("object with id %d is not exists", id));
        }

        System.out.println("Start delete");
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entityDelete);
        transaction.commit();
        System.out.println("Delete successfully");
        session.close();
    }
}
