package com.t3h.dao;

import com.t3h.entity.User;

import java.util.List;

public interface IUserDao extends GenericDao<User> {
    public List<User> getAllUsers();
    void saveOrUpdate(User user);
    public User findUserById(int id);
    public void delete(int id);
}
