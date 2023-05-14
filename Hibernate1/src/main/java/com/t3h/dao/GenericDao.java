package com.t3h.dao;

import com.t3h.entity.BaseEntity;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T extends BaseEntity> {
    T findById(Integer id);
    List<T> findAll();
    void create(T entity);
    T update(T entity);
    void deleteById(Integer id) throws SQLException;
}
